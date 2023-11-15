package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    /***********더미생성**********/
    Product createProduct(Product product);
    /***********더미생성**********/
    void addProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long productId); //id로 조회
    Optional<Product> getProductByName(String productName); //Name로 조회
    void updateProduct(Product updatedProduct);
    void deleteProductById(Long productId);//product id 로 지우기
    void deleteProductByName(String productName);//product name 으로 지우기
    void deleteAll();

    List<Product> getProductInfos(List<Long> values);
/*
    void mapProductToCategory(Long productId, Long categoryId); //add 후에 발동 //새로추가
    //test code짜기
*/
}
