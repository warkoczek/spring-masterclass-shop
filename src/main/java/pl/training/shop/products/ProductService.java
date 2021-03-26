package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.retry.Retry;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Retry
    public Product add(Product product){
        //return productRepository.save(product);
        throw new RuntimeException();
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize){
        return productRepository.findAll(pageNumber, pageSize);
    }
}
