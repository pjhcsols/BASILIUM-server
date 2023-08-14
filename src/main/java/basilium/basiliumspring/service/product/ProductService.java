package basilium.basiliumspring.service.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.repository.product.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

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
