package br.pucminas.leads.adapters.streams.in;

import br.pucminas.leads.adapters.streams.config.RedisConfiguration;
import br.pucminas.leads.adapters.streams.config.RedisStreamProperties;
import br.pucminas.leads.adapters.streams.support.RedisContainerSupport;
import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import br.pucminas.leads.application.ports.in.ReceiveLeadUseCase;
import io.lettuce.core.RedisBusyException;
import io.lettuce.core.RedisCommandExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.util.Collections;

import static br.pucminas.leads.adapters.streams.in.support.LeadDtoSupport.defaultLeadDto;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
@EnableConfigurationProperties(RedisStreamProperties.class)
@Import({ RedisConfiguration.class,  RedisAutoConfiguration.class, LeadStreamListener.class })
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@Slf4j
class LeadStreamListenerTest extends RedisContainerSupport {

    private final RedisStreamProperties properties;
    private final RedisTemplate<String, String> redisTemplate;

    @MockBean
    private ReceiveLeadUseCase useCase;

    @Test
    @DisplayName("Given Message When Receive Then Process")
    public void givenMessageWhenReceiveThenProcess() {
        var streamKey = this.properties.getKey();
        var leadDto = defaultLeadDto().build();
        var record = StreamRecords.newRecord()
                                   .ofObject(leadDto)
                                   .withStreamKey(streamKey);
        this.redisTemplate.opsForStream()
                          .add(record);

        await().atMost(Duration.ofSeconds(5))
               .untilAsserted(() ->  verify(this.useCase, times(1)).receive(any(Lead.class)));
    }

}