package br.pucminas.leads.adapters.email.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import io.awspring.cloud.ses.SimpleEmailServiceJavaMailSender;
import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsSesProperties.class)
public class AwsSesConfiguration {

    private final AwsSesProperties properties;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        var credentials = this.properties.getCredentials();
        var accessKey = credentials.getAccessKey();
        var secretKey = credentials.getSecretKey();
        var basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(this.properties.getEndpoint(), this.properties.getRegion()))
                .build();
    }

    @Bean
    public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
    }

    @Bean
    public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
    }

}
