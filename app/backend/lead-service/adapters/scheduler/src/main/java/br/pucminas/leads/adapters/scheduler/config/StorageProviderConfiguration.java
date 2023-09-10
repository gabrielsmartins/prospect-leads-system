package br.pucminas.leads.adapters.scheduler.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.nosql.redis.LettuceRedisStorageProvider;
import org.jobrunr.utils.mapper.jackson.JacksonJsonMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class StorageProviderConfiguration {

    @Value("${spring.application.name}")
    private String keyPrefix;

    @Bean
    public JobMapper jobMapper() {
        var jsonMapper = new JacksonJsonMapper();
        return new JobMapper(jsonMapper);
    }

    @Bean
    @Profile("!test")
    public StorageProvider storageProvider(LettuceConnectionFactory connectionFactory, JobMapper jobMapper) {
        var standaloneConfiguration = connectionFactory.getStandaloneConfiguration();
        var redisURI = RedisURI.builder()
                .withHost(standaloneConfiguration.getHostName())
                .withPassword(standaloneConfiguration.getPassword().get())
                .withPort(standaloneConfiguration.getPort())
                .withDatabase(standaloneConfiguration.getDatabase())
                .build();
        var redisClient = RedisClient.create(redisURI);
        var provider = new LettuceRedisStorageProvider(redisClient, keyPrefix);
        provider.setJobMapper(jobMapper);
        return provider;
    }

    @Bean
    @Profile("test")
    public StorageProvider inMemoryStorageProvider(JobMapper jobMapper) {
        var storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }

}
