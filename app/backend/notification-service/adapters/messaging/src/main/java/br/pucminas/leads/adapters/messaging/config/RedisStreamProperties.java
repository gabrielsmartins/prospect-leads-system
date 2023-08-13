package br.pucminas.leads.adapters.messaging.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ToString
@Getter
@Setter
@ConfigurationProperties(prefix = "redis.streams")
public class RedisStreamProperties {

    private String key;
    private String group;

}
