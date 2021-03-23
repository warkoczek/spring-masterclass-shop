package pl.training.shop.orders;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository
public class HashMapOrderRepository implements OrderRepository {

    private Map<Long, Order> orders = new HashMap<>();
    private Long index = 0l;

    @Override
    public Order save(Order order) {
        order.setId(++index);
        orders.put(index, order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public void update(Order order) {
        orders.replace(order.getId(), order);
    }
}
