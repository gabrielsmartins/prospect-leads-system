package br.pucminas.leads.adapters.messaging.in;

import br.pucminas.leads.adapters.messaging.in.dto.LeadDto;
import br.pucminas.leads.adapters.messaging.in.mapper.LeadSConsumerMapper;
import br.pucminas.leads.application.ports.in.SendNotificationUseCase;
import br.pucminas.leads.common.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;

import static net.logstash.logback.marker.Markers.append;

@MessagingAdapter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableConfigurationProperties(RedisStreamProperties.class)
@Slf4j
public class LeadConsumer implements StreamListener<String, ObjectRecord<String, LeadDto>> {

    private final RedisStreamProperties redisStreamProperties;
    private final RedisTemplate<String, String> redisTemplate;
    private final SendNotificationUseCase useCase;

    @Override
    public void onMessage(ObjectRecord<String, LeadDto> message) {
        var streamKey = this.redisStreamProperties.getKey();
        try {
            log.info(append("payload", message), "Receiving lead from: {}", streamKey);

            log.info(append("payload", message), "Mapping lead");
            var lead = LeadSConsumerMapper.mapToDomain(message.getValue());
            log.info(append("payload", lead), "Lead was mapped successfully");

            log.info(append("payload", lead), "Processing lead");
            this.useCase.process(lead);
            log.info(append("payload", message), "Lead was processed successfully");
        } catch (Exception e) {
            log.error(append("payload", message), "Error processing lead", e);
        } finally {
            redisTemplate.opsForStream().acknowledge(streamKey, message);
        }
    }

}
