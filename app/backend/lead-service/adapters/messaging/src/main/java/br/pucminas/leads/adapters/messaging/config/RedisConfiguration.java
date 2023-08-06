package br.pucminas.leads.adapters.messaging.config;

import br.pucminas.leads.adapters.messaging.in.dto.LeadDto;
import io.lettuce.core.RedisBusyException;
import io.lettuce.core.RedisCommandExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RedisConfiguration {

    private final StreamListener<String, ObjectRecord<String, LeadDto>> streamListener;
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final RedisStreamProperties properties;


    @Bean
    public Subscription subscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        var streamKey = this.properties.getKey();
        var group = this.properties.getGroup();
        this.createConsumerGroup(streamKey, group);

        var options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                                                    .builder()
                                                    .pollTimeout(Duration.ofSeconds(1))
                                                    .targetType(LeadDto.class)
                                                    .build();

        var listenerContainer = StreamMessageListenerContainer.create(redisConnectionFactory, options);
        var consumer = Consumer.from(streamKey, InetAddress.getLocalHost().getHostName());
        var streamOffset = StreamOffset.create(streamKey, ReadOffset.lastConsumed());
        var subscription = listenerContainer.receive(consumer, streamOffset, streamListener);
        listenerContainer.start();
        return subscription;
    }

    private void createConsumerGroup(String key, String group) {
        try {
            redisTemplate.opsForStream()
                         .createGroup(key, group);
        } catch (RedisSystemException e) {
            if (e.getRootCause() instanceof RedisBusyException) {
                log.info("STREAM - Redis group already exists, skipping Redis group creation: {}", group);
            } else if (e.getRootCause() instanceof RedisCommandExecutionException) {
                log.info("STREAM - Stream does not yet exist, creating empty stream {}", key);
                redisTemplate.opsForStream().add(key, Collections.singletonMap("", ""));
                redisTemplate.opsForStream().createGroup(key, group);
            } else throw e;
        }
    }

}

