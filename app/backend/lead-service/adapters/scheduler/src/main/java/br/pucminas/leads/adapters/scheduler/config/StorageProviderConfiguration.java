package br.pucminas.leads.adapters.scheduler.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.nosql.redis.LettuceRedisStorageProvider;
import org.jobrunr.utils.mapper.jackson.JacksonJsonMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class StorageProviderConfiguration {

    @Value("${spring.application.name}")
    private String keyPrefix;

    @Bean
    public StorageProvider storageProvider(LettuceConnectionFactory connectionFactory) {
        var standaloneConfiguration = connectionFactory.getStandaloneConfiguration();
        var redisURI = RedisURI.builder()
                .withHost(standaloneConfiguration.getHostName())
                .withPassword(standaloneConfiguration.getPassword().get())
                .withPort(standaloneConfiguration.getPort())
                .withDatabase(standaloneConfiguration.getDatabase())
                .build();
        var redisClient = RedisClient.create(redisURI);
        var provider = new LettuceRedisStorageProvider(redisClient, keyPrefix);
        var jsonMapper = new JacksonJsonMapper();
        var jobMapper = new JobMapper(jsonMapper);
        provider.setJobMapper(jobMapper);
        return provider;
    }

}
