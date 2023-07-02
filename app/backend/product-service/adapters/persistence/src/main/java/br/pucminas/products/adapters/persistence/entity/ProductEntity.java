package br.pucminas.products.adapters.persistence.entity;


import br.pucminas.products.adapters.persistence.entity.converter.CategoryEnumConverter;
import br.pucminas.products.application.domain.enums.CategoryEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder(setterPrefix = "with")
@Table(name = "tbl_product")
@Entity
@SelectBeforeUpdate(value = false)
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", columnDefinition = "serial", nullable = false)
    private Integer id;

    @Column(name = "product_name", columnDefinition = "varchar(256)", nullable = false)
    private String name;

    @Column(name = "product_category", columnDefinition = "char(1)", nullable = false)
    @Convert(converter = CategoryEnumConverter.class)
    private CategoryEnum category;

    @Column(name = "product_active", columnDefinition = "boolean", nullable = false)
    private Boolean active;

    @Column(name = "created_at", columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "timestamp", nullable = true)
    private LocalDateTime deletedAt;
}
