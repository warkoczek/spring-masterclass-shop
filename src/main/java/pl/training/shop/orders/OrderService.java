package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.common.validator.InvalidOrderException;
import pl.training.shop.common.validator.Validate;
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order add(@Validate(exception = InvalidOrderException.class) Order order){
        return orderRepository.save(order);
    }

    public Order getBy(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public void update(Order order){
        orderRepository.save(order);
    }
}
