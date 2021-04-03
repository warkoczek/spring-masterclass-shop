package pl.training.shop.products;

import lombok.*;
import org.hibernate.annotations.Columns;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;
@Table(name = "products", indexes = @Index(name = "product_type", columnList = "type"))
@Entity
@Data
@EqualsAndHashCode(exclude = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @Columns(columns = {
            @Column(name = "currency", length = 3),
            @Column(name = "value", length = 15)
    })
    private FastMoney price;
    @Enumerated(EnumType.STRING)
    private ProductType type;
}
