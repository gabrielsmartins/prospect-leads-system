package br.pucminas.leads;

import br.pucminas.leads.adapters.streams.support.RedisContainerSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class LeadServiceApplicationTest extends RedisContainerSupport {

    @Test
    void contextLoads() {

    }

}
