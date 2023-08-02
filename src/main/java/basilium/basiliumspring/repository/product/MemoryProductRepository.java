package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;

import java.util.*;

public class MemoryProductRepository implements ProductRepository {

    private Map<Long, Product> products;

    public MemoryProductRepository() {
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
    public void deleteProductById(Long productId) {
        products.remove(productId);
    }

    @Override
    public void deleteProductByName(String productName) {
        Iterator<Map.Entry<Long, Product>> iterator = products.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Product> entry = iterator.next();
            Product product = entry.getValue();
            if (product.getProductName().equals(productName)) {
                iterator.remove();
            }
        }

    }


}



