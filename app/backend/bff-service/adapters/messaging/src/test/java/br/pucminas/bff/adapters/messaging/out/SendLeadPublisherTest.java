package br.pucminas.bff.adapters.messaging.out;

import br.pucminas.bff.adapters.messaging.config.RedisStreamProperties;
import br.pucminas.bff.adapters.messaging.support.RedisContainerSupport;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
@EnableConfigurationProperties(RedisStreamProperties.class)
@Import({ RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class, CaptureLeadPublisher.class })
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
class SendLeadPublisherTest extends RedisContainerSupport {

    private final CaptureLeadPublisher publisher;

    @Test
    @DisplayName("Given Insurance Quote Id When Is Valid Then Send")
    public void givenInsuranceQuoteIdWhenIsValidThenSend() {
        var insuranceQuoteId = UUID.randomUUID();
        var productId = 1;
        this.publisher.capture(insuranceQuoteId)
                      .as(StepVerifier::create)
                      .verifyComplete();
    }

}