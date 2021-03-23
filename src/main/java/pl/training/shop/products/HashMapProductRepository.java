package pl.training.shop.products;

import org.springframework.stereotype.Repository;
import pl.training.shop.common.PagedResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Repository
public class HashMapProductRepository implements ProductRepository {
    private Map<Long, Product> products = new HashMap<>();
    private Long index = 0l;

    @Override
    public Product save(Product product) {
        product.setId(++index);
        products.put(index, product);
        return product;
    }

    @Override
    public PagedResult<Product> findAll(int pageNumber, int pageSize) {
        var totalPages = products.size() / pageSize;
        var data = new ArrayList<>(products.values());
        return new PagedResult<>(data, pageNumber, totalPages);
    }
}
