package br.pucminas.products.adapters.persistence.repository;

import br.pucminas.products.adapters.persistence.support.DatabaseContainer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static br.pucminas.products.adapters.persistence.support.ProductEntitySupport.defaultProductEntity;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // deactivate the default behaviour
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ProductRepositoryTest extends DatabaseContainer {

    private final ProductRepository repository;

    @Test
    @DisplayName("Given Product When Save Then Return Saved Product")
    public void givenProductWhenSaveThenReturnSavedProduct() {
        var productEntity = defaultProductEntity()
                .withId(null)
                .build();

        var savedProductEntity = this.repository.save(productEntity);

        assertThat(savedProductEntity).isNotNull();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Existing Product")
    public void givenIdWhenExistsThenReturnExistingProduct() {
        var productEntity = defaultProductEntity()
                .withId(null)
                .build();

        this.repository.save(productEntity);

        var optionalProductEntity = this.repository.findById(productEntity.getId());

        assertThat(optionalProductEntity).isPresent();
    }

}