package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;

import java.util.List;

public interface ProductRepository {
    void addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long productId); //id로 조회
    Product getProductByName(String productName); //Name로 조회
    void updateProduct(Product updatedProduct);
    void deleteProductById(Long productId);//product id 로 지우기
    void deleteProductByName(String productName);//product name 으로 지우기
    //test code짜기
}
