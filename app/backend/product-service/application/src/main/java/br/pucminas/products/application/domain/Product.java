package br.pucminas.products.application.domain;

import br.pucminas.products.application.domain.enums.CategoryEnum;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class Product {

    private Integer id;
    private String name;
    private CategoryEnum category;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
