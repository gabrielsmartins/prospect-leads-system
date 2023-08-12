package br.pucminas.leads.adapters.persistence.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.dynamodb")
@Getter
@Setter
@ToString
public class AwsDynamoDBConfigurationProperties {

    private String endpoint;
    private String region;
    private AwsDynamoDBConfigurationCredentialsProperties credentials;

    @Getter
    @Setter
    @ToString
    public static class AwsDynamoDBConfigurationCredentialsProperties {

        private String accessKey;
        private String secretKey;

    }

}
