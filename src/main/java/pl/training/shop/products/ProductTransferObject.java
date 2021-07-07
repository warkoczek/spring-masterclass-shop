package pl.training.shop.products;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ProductTransferObject extends RepresentationModel<ProductTransferObject> {

    @NotEmpty
    private String name;
    @Size(min = 3, max = 255)
    private String description;

    private String price;

    private ProductTypeTransferObject type;
}
