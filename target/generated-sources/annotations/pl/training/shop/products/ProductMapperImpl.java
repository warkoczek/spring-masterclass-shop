package pl.training.shop.products;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-12T15:51:32+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductTransferObject productTransferObject) {
        if ( productTransferObject == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }

    @Override
    public ProductTransferObject toProductTransferObject(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductTransferObject productTransferObject = new ProductTransferObject();

        return productTransferObject;
    }

    @Override
    public PagedResultTransferObject<ProductTransferObject> toProductTransferObjectsPage(PagedResult<Product> productsPage) {
        if ( productsPage == null ) {
            return null;
        }

        PagedResultTransferObject<ProductTransferObject> pagedResultTransferObject = new PagedResultTransferObject<ProductTransferObject>();

        return pagedResultTransferObject;
    }

    @Override
    public ProductTypeTransferObject toProductTypeTransferObject(ProductType productType) {
        if ( productType == null ) {
            return null;
        }

        ProductTypeTransferObject productTypeTransferObject;

        switch ( productType ) {
            case BOOK: productTypeTransferObject = ProductTypeTransferObject.EBOOK;
            break;
            case AUDIO: productTypeTransferObject = ProductTypeTransferObject.MUSIC;
            break;
            case VIDEO: productTypeTransferObject = ProductTypeTransferObject.VIDEO;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + productType );
        }

        return productTypeTransferObject;
    }

    @Override
    public ProductType toProductType(ProductTypeTransferObject productTypeTransferObject) {
        if ( productTypeTransferObject == null ) {
            return null;
        }

        ProductType productType;

        switch ( productTypeTransferObject ) {
            case EBOOK: productType = ProductType.BOOK;
            break;
            case MUSIC: productType = ProductType.AUDIO;
            break;
            case VIDEO: productType = ProductType.VIDEO;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + productTypeTransferObject );
        }

        return productType;
    }
}
