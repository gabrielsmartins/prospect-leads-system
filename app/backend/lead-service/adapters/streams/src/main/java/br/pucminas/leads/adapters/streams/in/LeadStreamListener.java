package br.pucminas.leads.adapters.streams.in;

import br.pucminas.leads.adapters.streams.config.RedisStreamProperties;
import br.pucminas.leads.adapters.streams.in.dto.LeadDto;
import br.pucminas.leads.adapters.streams.in.mapper.LeadStreamListenerMapper;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.common.stereotype.StreamAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;

import static net.logstash.logback.marker.Markers.append;

@StreamAdapter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableConfigurationProperties(RedisStreamProperties.class)
@Slf4j
public class LeadStreamListener implements StreamListener<String, ObjectRecord<String, LeadDto>> {

    private final RedisStreamProperties redisStreamProperties;
    private final RedisTemplate<String, String> redisTemplate;
    private final ProcessLeadUseCase useCase;

    @Override
    public void onMessage(ObjectRecord<String, LeadDto> message) {
        var streamKey = this.redisStreamProperties.getKey();
        try {
            log.info(append("payload", message), "Receiving lead from: {}", streamKey);

            log.info(append("payload", message), "Mapping lead");
            var lead = LeadStreamListenerMapper.mapToDomain(message.getValue());
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
