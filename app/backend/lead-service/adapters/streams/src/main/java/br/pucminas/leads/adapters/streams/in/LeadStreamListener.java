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
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.stream.StreamListener;

import java.util.List;

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
            var leadDto = message.getValue();
            log.info(append("payload", message), "Receiving lead from: {}", streamKey);
            log.info(append("payload", message), "Mapping lead");
            var lead = LeadStreamListenerMapper.mapToDomain(leadDto);
            log.info(append("payload", lead), "Lead was mapped successfully");

            log.info(append("payload", lead), "Processing lead");
            this.useCase.process(lead);
            log.info(append("payload", message), "Lead was processed successfully");
            acknowledgeAndDelete(message, streamKey);
        } catch (Exception e) {
            log.error(append("payload", message), "Error processing lead", e);
            acknowledgeAndDelete(message, streamKey);
        }
    }

    private void acknowledgeAndDelete(ObjectRecord<String, LeadDto> message, String streamKey) {
        this.redisTemplate.opsForStream().acknowledge(streamKey, message);
        this.redisTemplate.opsForStream().delete(message);
    }

    private void reDrive(ObjectRecord<String, LeadDto> message) {
        var streamKey = message.getStream();
        this.redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForStream().acknowledge(streamKey, message);
                operations.opsForStream().delete(message);
                var record = StreamRecords.newRecord()
                                          .ofObject(message.getValue())
                                          .withStreamKey(streamKey);
                operations.opsForStream()
                          .add(record);
                return operations.exec();
            }
        });

    }

}
