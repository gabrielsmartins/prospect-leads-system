package br.pucminas.bff.adapters.messaging.out;

import br.pucminas.bff.adapters.messaging.config.RedisStreamProperties;
import br.pucminas.bff.adapters.messaging.out.dto.LeadDto;
import br.pucminas.bff.application.ports.out.leads.SendLeadPort;
import br.pucminas.bff.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static net.logstash.logback.marker.Markers.append;


@MessagingAdapter
@EnableConfigurationProperties(RedisStreamProperties.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SendLeadPublisher implements SendLeadPort {

    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final RedisStreamProperties properties;

    @Override
    public Mono<Void> send(UUID insuranceQuoteId) {
        var leadDto = new LeadDto(insuranceQuoteId, LocalDateTime.now());
        var streamKey = this.properties.getKey();
        log.info(append("lead", leadDto).and(append("stream_key", streamKey)), "Sending lead");
        var record = StreamRecords.newRecord()
                                  .ofObject(leadDto)
                                  .withStreamKey(streamKey);
        return this.redisTemplate.opsForStream()
                                 .add(record)
                                 .doOnNext(recordId -> log.info(append("record", recordId), "Message was sent successfully"))
                                 .then();
    }

}
