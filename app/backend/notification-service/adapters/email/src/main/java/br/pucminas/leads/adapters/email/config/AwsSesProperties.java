package br.pucminas.leads.adapters.email.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "spring.cloud.aws.ses")
public class AwsSesProperties {

    private String region;
    private String endpoint;
    private AwsSesPropertiesCredentials credentials;

    @Getter
    @Setter
    @ToString
    public static class AwsSesPropertiesCredentials {

        private String accessKey;
        private String secretKey;

    }

}
