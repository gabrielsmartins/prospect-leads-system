package br.pucminas.leads.adapters.messaging.config;

import br.pucminas.leads.adapters.messaging.in.dto.LeadDto;
import io.lettuce.core.RedisBusyException;
import io.lettuce.core.RedisCommandExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class RedisConfiguration {

    private final ReactiveRedisTemplate<String, String> redisTemplate;
    @Bean
    public StreamReceiver<String, ObjectRecord<String, LeadDto>> receiver(ReactiveRedisConnectionFactory factory) {
         var options = StreamReceiver.StreamReceiverOptions.builder()
                 .targetType(LeadDto.class)
                 .pollTimeout(Duration.ofSeconds(5))
                 .build();
         return StreamReceiver.create(factory, options);
    }

    public void createConsumerGroup(String key, String group) {
        try {
            this.redisTemplate.getConnectionFactory()
                    .getReactiveConnection()
                    .streamCommands()
                    .xGroupCreate(ByteBuffer.wrap(key.getBytes()), group, ReadOffset.from("0-0"), true)
                    .block();
            var consumer = Consumer.from(key, InetAddress.getLocalHost().getHostName());
        } catch (RedisSystemException e) {
            if (e.getRootCause() instanceof RedisBusyException) {
                log.info("STREAM - Redis group already exists, skipping Redis group creation: {}", group);
            } else if (e.getRootCause() instanceof RedisCommandExecutionException) {
                log.info("STREAM - Stream does not yet exist, creating empty stream {}", key);
                redisTemplate.opsForStream().add(key, Collections.singletonMap("", ""));
                redisTemplate.opsForStream().createGroup(key, group);
            } else throw e;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}

