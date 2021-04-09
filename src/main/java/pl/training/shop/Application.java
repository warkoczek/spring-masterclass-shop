package pl.training.shop;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.orders.Order;
import pl.training.shop.payments.*;
import pl.training.shop.products.Product;
import pl.training.shop.products.ProductType;

import java.util.List;

@Log
public class Application {
    private static final String BASE_PACKAGE = "pl.training.shop";

    private static final Product VIDEO_PRODUCT = Product.builder()
            .name("Spring masterclass")
            .description("Spring framework practical course")
            .price(LocalMoney.of(999))
            .type(ProductType.VIDEO)
            .build();

    private static final Product BOOK_PRODUCT = Product.builder()
            .name("Spring guide")
            .description("Exercises to do")
            .price(LocalMoney.of(99))
            .type(ProductType.BOOK)
            .build();

    private static final Product AUDIO_PRODUCT = Product.builder()
            .name("Spring Cache")
            .description("Caching makes it easy")
            .price(LocalMoney.of(230))
            .type(ProductType.AUDIO)
            .build();

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE)){
            var shopService = applicationContext.getBean(ShopService.class);
            shopService.addProduct(VIDEO_PRODUCT);
            shopService.addProduct(BOOK_PRODUCT);
            log.info(shopService.getProducts(0, 100).toString());

            var order = new Order(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));
            shopService.placeOrder(order);
            var payment = shopService.payForOrder(order.getId());
            log.info(payment.getId());

            log.info(shopService.getByName("Spring").toString());
            shopService.addProduct(AUDIO_PRODUCT);
            log.info(shopService.getByName("Spring").toString());

        }
    }
}
