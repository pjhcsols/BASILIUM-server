package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.category.Category;
import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.repository.category.CategoryRepository;
//import basilium.basiliumspring.repository.category.MemoryCategoryRepository;

import java.util.*;

public class MemoryProductRepository implements ProductRepository {

    private Map<Long, Product> products;
    private Map<Long, Long> productCategoryMap = new HashMap<>();
    public MemoryProductRepository() {
        this.products = new HashMap<>();
    }
    // 새로운 상품 ID를 위해 다음 사용 가능한 ID+1를 생성하는 유틸리티 메서드 추가
    private Long generateNextProductId() {
        if (products.isEmpty()) {
            return 1L; // 첫 번째 상품 ID는 1로 시작합니다.
        } else {
            long maxId = products.keySet().stream().mapToLong(Long::longValue).max().getAsLong();
            return maxId + 1;
        }
    }
    // 새로운 상품 추가 시, 새로운 ID를 생성하여 상품에 할당
    @Override
    public void addProduct(Product product) {
        long newProductId = generateNextProductId();
        product.setProductId(newProductId); // 새로운 ID 할당
        products.put(newProductId, product);
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
    public Product getProductByName(String productName) {
        for (Product product : products.values()) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }
        return null;
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
/*
    //카테고리와 상품 id 매핑하는 곳 서비스에서 add시에 발동
    @Override
    public void mapProductToCategory(Long productId, Long categoryId) {
        productCategoryMap.put(productId, categoryId);
    }
*/
}



