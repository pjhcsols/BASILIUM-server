package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.category.Category;
import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.repository.category.CategoryRepository;
import basilium.basiliumspring.repository.category.MemoryCategoryRepository;

import java.util.*;

public class MemoryProductRepository implements ProductRepository {

    private Map<Long, Product> products;

    public MemoryProductRepository() {
        this.products = new HashMap<>();
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


    //밑에 3개 테스트케이스 및 다시 짜기********
    // 새로운 상품 ID를 위해 다음 사용 가능한 ID+1를 생성하는 유틸리티 메서드 추가
    private Long generateNextProductId() {
        if (products.isEmpty()) {
            return 1L; // 첫 번째 상품 ID는 1로 시작합니다.
        } else {
            long maxId = products.keySet().stream().mapToLong(Long::longValue).max().getAsLong();
            return maxId + 1;
        }
    }
    /*지우기
    @Override
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }
    */
    // 새로운 상품 추가 시, 새로운 ID를 생성하여 상품에 할당
    @Override
    public void addProduct(Product product) {
        long newProductId = generateNextProductId();
        product.setProductId(newProductId); // 새로운 ID 할당
        products.put(newProductId, product);
    }

    // getProductCategory 메서드를 추가하여 categoryId와 연결된 Category를 반환하도록 합니다.
    // Product 객체에 productCategoryId가 있다고 가정하고 저장합니다.
    // 또는 CategoryId를 따로 설정하거나 가져오는 로직을 여기에 구현합니다.
    /*
    @Override
    public Category getProductCategory(Product product) {
        Long categoryId = product.getProductCategoryId(); // Product 객체의 productCategoryId를 가져옵니다.
        // 이제 categoryId를 이용하여 해당 Category를 조회하여 반환하는 로직을 작성합니다.
        // CategoryRepository를 사용하여 해당 categoryId를 이용해 Category를 조회하면 됩니다.
        // (CategoryRepository는 여기서 추가로 구현되어 있다고 가정합니다.)

        // 가정: CategoryRepository가 이미 구현되어 있다고 가정하고 호출하는 예시
        CategoryRepository categoryRepository = new MemoryCategoryRepository(); // CategoryRepository의 구현 클래스
        Category category = categoryRepository.getCategoryById(categoryId);

        // 또는 다른 방식으로 CategoryRepository를 주입받아 사용할 수 있습니다.

        return category;
    }
*/

}



