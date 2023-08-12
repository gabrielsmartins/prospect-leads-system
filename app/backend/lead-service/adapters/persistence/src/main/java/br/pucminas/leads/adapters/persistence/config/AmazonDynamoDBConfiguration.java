package br.pucminas.leads.adapters.persistence.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsDynamoDBConfigurationProperties.class)
public class AmazonDynamoDBConfiguration {

    private final AwsDynamoDBConfigurationProperties properties;

    @Bean
    public AmazonDynamoDB dynamoDbClient() {
        var endpoint = properties.getEndpoint();
        var region = properties.getRegion();
        var credentials = this.properties.getCredentials();
        var accessKey = credentials.getAccessKey();
        var secretKey = credentials.getSecretKey();
        var anonymousAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        var awsCredProvider = new AWSStaticCredentialsProvider(anonymousAWSCredentials);
        return AmazonDynamoDBClientBuilder.standard()
                                         .withCredentials(awsCredProvider)
                                          .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                                          .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB){
        return new DynamoDBMapper(amazonDynamoDB);
    }

}
