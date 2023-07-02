package br.pucminas.products;

import br.pucminas.products.adapters.persistence.support.DatabaseContainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceApplicationTests extends DatabaseContainer {

	@Test
	void contextLoads() {
	}

}
