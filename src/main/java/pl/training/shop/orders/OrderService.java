package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.common.validator.InvalidOrderException;
import pl.training.shop.common.validator.Validate;
import pl.training.shop.payments.Payment;

import java.time.Instant;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order add(@Validate(exception = InvalidOrderException.class) Order order){
        order.setTimestamp(Instant.now());
        order.setPayment(Payment.builder()
                .id(UUID.randomUUID().toString())
                .money(order.getTotalPrice())
                .timestamp(Instant.now())
                .build());
        return orderRepository.save(order);
    }

    public Order getBy(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public void update(Order order){
        orderRepository.save(order);
    }
}
