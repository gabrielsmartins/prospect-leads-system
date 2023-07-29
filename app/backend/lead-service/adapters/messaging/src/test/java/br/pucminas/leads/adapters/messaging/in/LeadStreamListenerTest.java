package br.pucminas.leads.adapters.messaging.in;

import br.pucminas.leads.adapters.messaging.config.RedisStreamProperties;
import br.pucminas.leads.adapters.messaging.support.RedisContainerSupport;
import br.pucminas.leads.application.domain.Lead;
import br.pucminas.leads.application.ports.in.ProcessLeadUseCase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.pucminas.leads.adapters.messaging.in.support.LeadDtoSupport.defaultLeadDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
@EnableConfigurationProperties(RedisStreamProperties.class)
@Import({ RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class, LeadStreamListener.class })
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
class LeadStreamListenerTest extends RedisContainerSupport {

    private final RedisStreamProperties properties;
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @MockBean
    private ProcessLeadUseCase useCase;

    @Test
    @DisplayName("Given Message When Receive Then Process")
    public void givenMessageWhenReceiveThenProcess() {
        var leadDto = defaultLeadDto().build();
        var streamKey = this.properties.getKey();
        var record = StreamRecords.newRecord()
                                   .ofObject(leadDto)
                                   .withStreamKey(streamKey);
        this.redisTemplate.opsForStream()
                          .add(record)
                          .block();

        verify(this.useCase, times(1)).process(any(Lead.class));
    }



}