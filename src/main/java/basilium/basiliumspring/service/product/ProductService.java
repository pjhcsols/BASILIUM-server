package basilium.basiliumspring.service.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.repository.product.ProductRepository;

import java.util.List;

//브랜드 유저가 add를 했을때 카테고리id와 product id 매핑
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product); // 상품 추가
    }
/*
    public void addProductAndMapToCategory(Product product, Long categoryId) {
        // addProduct(product); // 상품 추가

        // 상품 ID와 카테고리 ID를 매핑
        productRepository.mapProductToCategory(product.getProductCategoryId(), categoryId);
    }
*/
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(Long productId) {
        return productRepository.getProductById(productId);
    }

    public void updateProduct(Product updatedProduct) {
        productRepository.updateProduct(updatedProduct);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteProductById(productId);
    }

    public void deleteProductByName(String productName) {
        productRepository.deleteProductByName(productName);
    }
}
