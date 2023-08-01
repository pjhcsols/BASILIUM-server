package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class MemoryProduct implements ProductRepository {

    private Map<Long, Product> products;

    public MemoryProduct() {
        this.products = new HashMap<>();
    }

    @Override
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(Long productId) {
        return products.get(productId);
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        if (products.containsKey(updatedProduct.getProductId())) {
            products.put(updatedProduct.getProductId(), updatedProduct);
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + updatedProduct.getProductId());
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        products.remove(productId);
    }
}


