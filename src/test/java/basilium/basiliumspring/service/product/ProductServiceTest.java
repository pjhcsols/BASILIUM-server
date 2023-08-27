package basilium.basiliumspring.service.product;

import basilium.basiliumspring.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        // 테스트 전에 초기화 또는 데이터베이스 상태 설정 가능
        // 예: 테스트 데이터베이스를 클리어하거나 초기 데이터 추가
        // entityManager.createQuery("DELETE FROM Product").executeUpdate();
    }

    @Test
    void testAddProduct() {
        Product product1 = new Product();
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product1");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description1");
        product1.setProductPhotoUrl("http://example.com/test1.jpg");

        productService.addProduct(product1);

        // 트랜잭션을 커밋하고 영속성 컨텍스트를 클리어하여 데이터베이스에 변경 내용을 반영
        entityManager.flush();
        entityManager.clear();

        Product retrievedProduct = productService.getProductById(product1.getProductId());
        assertNotNull(retrievedProduct);
        assertEquals("Test Product1", retrievedProduct.getProductName());
    }

    @Test
    void testUpdateProduct() {
        Product product1 = new Product();
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product1");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description1");
        product1.setProductPhotoUrl("http://example.com/test1.jpg");

        productService.addProduct(product1);

        // 트랜잭션을 커밋하고 영속성 컨텍스트를 클리어하여 데이터베이스에 변경 내용을 반영
        entityManager.flush();
        entityManager.clear();

        Long productId = product1.getProductId();

        Product updatedProduct = new Product();
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName("Updated Product1");
        updatedProduct.setProductPrice(200L);
        updatedProduct.setProductDesc("Updated Description1");
        updatedProduct.setProductPhotoUrl("http://example.com/updated1.jpg");

        productService.updateProduct(updatedProduct);

        // 트랜잭션을 커밋하고 영속성 컨텍스트를 클리어하여 데이터베이스에 변경 내용을 반영
        entityManager.flush();
        entityManager.clear();

        Product retrievedProduct = productService.getProductById(productId);
        assertNotNull(retrievedProduct);
        assertEquals("Updated Product1", retrievedProduct.getProductName());
        assertEquals(200L, retrievedProduct.getProductPrice());
    }

    @Test
    void testDeleteProduct() {
        Product product1 = new Product();
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product1");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description1");
        product1.setProductPhotoUrl("http://example.com/test1.jpg");

        productService.addProduct(product1);

        // 트랜잭션을 커밋하고 영속성 컨텍스트를 클리어하여 데이터베이스에 변경 내용을 반영
        entityManager.flush();
        entityManager.clear();

        Long productId = product1.getProductId();

        productService.deleteProductById(productId);

        // 트랜잭션을 커밋하고 영속성 컨텍스트를 클리어하여 데이터베이스에 변경 내용을 반영
        entityManager.flush();
        entityManager.clear();

        // getProductById가 Optional을 반환하므로 try-catch 블록을 사용하여 상품이 있는지 확인
        try {
            Product retrievedProduct = productService.getProductById(productId);
            assertNull(retrievedProduct);
        } catch (NoSuchElementException e) {
            // 상품이 없어야 하는 경우
            assertTrue(true);
        }
    }




    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        // 추가적인 검증 로직을 추가할 수 있습니다.
    }
}