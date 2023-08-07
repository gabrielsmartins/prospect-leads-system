package br.pucminas.leads.adapters.messaging.in;

import br.pucminas.leads.adapters.messaging.config.RedisStreamProperties;
import br.pucminas.leads.adapters.messaging.in.dto.LeadDto;
import br.pucminas.leads.adapters.messaging.in.mapper.LeadStreamListenerMapper;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.common.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamReceiver;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;

import static net.logstash.logback.marker.Markers.append;

@MessagingAdapter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableConfigurationProperties(RedisStreamProperties.class)
@Slf4j
public class LeadStreamListener {

    private final RedisStreamProperties redisStreamProperties;
    private final StreamReceiver<String, ObjectRecord<String, LeadDto>> streamReceiver;
    private Disposable subscription;
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final ProcessLeadUseCase useCase;

    @PostConstruct
    public void consume() {
        var streamKey = this.redisStreamProperties.getKey();
        var group = this.redisStreamProperties.getGroup();
        var leads = this.streamReceiver.receive(Consumer.from(group, getConsumerName()), StreamOffset.create(streamKey, ReadOffset.lastConsumed()));
        subscription = leads.flatMap(this::process)
                            .subscribe();
    }

    @SneakyThrows
    private static String getConsumerName() {
        return InetAddress.getLocalHost().getHostName();
    }

    @PreDestroy
    private void preDestroy() {
        if (subscription != null) {
            subscription.dispose();
            subscription = null;
        }
    }

    public Mono<Void> process(ObjectRecord<String, LeadDto> message) {
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
        return Mono.empty().then();
    }

}
