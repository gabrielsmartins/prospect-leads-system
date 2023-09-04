package br.pucminas.bff.adapters.streams.out;

import br.pucminas.bff.adapters.streams.config.RedisStreamProperties;
import br.pucminas.bff.adapters.streams.out.dto.LeadDto;
import br.pucminas.bff.application.ports.out.leads.CaptureLeadPort;
import br.pucminas.bff.common.stereotype.StreamAdapter;
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


@StreamAdapter
@EnableConfigurationProperties(RedisStreamProperties.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CaptureLeadPublisher implements CaptureLeadPort {

    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final RedisStreamProperties properties;

    @Override
    public Mono<Void> capture(UUID insuranceQuoteId) {
        var streamKey = this.properties.getKey();
        var leadDto = new LeadDto(insuranceQuoteId, LocalDateTime.now());
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
