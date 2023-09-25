package br.pucminas.leads;

import br.pucminas.leads.adapters.email.support.LocalstackSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, controlledShutdown = true)
public class NotificationServiceApplicationTest extends LocalstackSupport {

    @Test
    void contextLoads() {

    }

}
