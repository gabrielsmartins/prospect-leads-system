package br.pucminas.leads.adapters.streams.config;

import br.pucminas.leads.adapters.streams.in.dto.LeadDto;
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
import org.springframework.data.redis.core.RedisTemplate;
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
    private final RedisStreamProperties properties;

    @Bean
    public Subscription subscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        var streamKey = this.properties.getKey();
        var group = this.properties.getGroup();
        this.createConsumerGroup(redisConnectionFactory, streamKey, group);
        var options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                                                    .builder()
                                                    .pollTimeout(Duration.ofSeconds(5))
                                                    .targetType(LeadDto.class)
                                                    .build();
        var listenerContainer = StreamMessageListenerContainer.create(redisConnectionFactory, options);
        var consumer = Consumer.from(group, InetAddress.getLocalHost().getHostName());
        var streamOffset = StreamOffset.create(streamKey, ReadOffset.lastConsumed());
        var subscription = listenerContainer.receive(consumer, streamOffset, streamListener);
        listenerContainer.start();
        return subscription;
    }

    private void createConsumerGroup(RedisConnectionFactory redisConnectionFactory, String streamKey, String group) {
        try {
            redisConnectionFactory.getConnection()
                                  .xGroupCreate(streamKey.getBytes(), group, ReadOffset.from("0-0"), true);
        } catch (RedisSystemException exception) {
            log.warn(exception.getCause().getMessage());
        }
    }

}

