package pl.training.shop.orders;

import lombok.*;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.payments.LocalMoney;
import pl.training.shop.payments.Payment;
import pl.training.shop.products.Product;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Order {

    private Long id;
    @NotEmpty
    @NonNull
    private List<Product> products;

    private Payment payment;

    public FastMoney getTotalPrice(){
        return products.stream()
                .map(Product::getPrice)
                .reduce(LocalMoney.zero(), FastMoney::add);
    }
}
