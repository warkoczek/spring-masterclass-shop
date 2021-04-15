package pl.training.shop.products;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.FastMoneyMapper;
import pl.training.shop.common.web.PagedResultTransferObject;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-15T11:56:09+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private FastMoneyMapper fastMoneyMapper;

    @Override
    public Product toProduct(ProductTransferObject productTransferObject) {
        if ( productTransferObject == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productTransferObject.getName() );
        product.setDescription( productTransferObject.getDescription() );
        product.setPrice( fastMoneyMapper.toFastMoney( productTransferObject.getPrice() ) );
        product.setType( toProductType( productTransferObject.getType() ) );

        return product;
    }

    @Override
    public ProductTransferObject toProductTransferObject(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductTransferObject productTransferObject = new ProductTransferObject();

        productTransferObject.setName( product.getName() );
        productTransferObject.setDescription( product.getDescription() );
        productTransferObject.setPrice( fastMoneyMapper.toPrice( product.getPrice() ) );
        productTransferObject.setType( toProductTypeTransferObject( product.getType() ) );

        return productTransferObject;
    }

    @Override
    public PagedResultTransferObject<ProductTransferObject> toProductTransferObjectsPage(PagedResult<Product> productsPage) {
        if ( productsPage == null ) {
            return null;
        }

        PagedResultTransferObject<ProductTransferObject> pagedResultTransferObject = new PagedResultTransferObject<ProductTransferObject>();

        pagedResultTransferObject.setData( productListToProductTransferObjectList( productsPage.getData() ) );
        pagedResultTransferObject.setPageNumber( productsPage.getPageNumber() );
        pagedResultTransferObject.setTotalPages( productsPage.getTotalPages() );

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

    protected List<ProductTransferObject> productListToProductTransferObjectList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductTransferObject> list1 = new ArrayList<ProductTransferObject>( list.size() );
        for ( Product product : list ) {
            list1.add( toProductTransferObject( product ) );
        }

        return list1;
    }
}
