package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;

import java.util.List;

public interface ProductRepository {
    void addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    void updateProduct(Product updatedProduct);
    void deleteProduct(Long productId);

    //product id 로 지우기
    //product name 으로 지우기
    //test code짜기
}
