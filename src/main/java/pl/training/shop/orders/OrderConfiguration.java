package pl.training.shop.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.EntityManager;

@Configuration
public class OrderConfiguration {

    @Bean
    public OrderService orderService(OrderRepository orderRepository){
        return new OrderService(orderRepository);
    }



}
