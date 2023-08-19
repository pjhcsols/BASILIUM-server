package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaProductRepositoryTest {

    @Autowired
    private JpaProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DirtiesContext
    void addProduct() {
        Product product = new Product(
                null, // productId is auto-generated
                1L, // productCategoryId
                "Test Product",
                100L, // productPrice
                "Test Description",
                "http://example.com/test.jpg"
        );

        productRepository.addProduct(product);

        Product savedProduct = entityManager.find(Product.class, product.getProductId());
        assertEquals("Test Product", savedProduct.getProductName());
    }

    @Test
    void getAllProducts() {
        Product product1 = new Product(null, 1L, "Product 1", 50L, "Description 1", "http://example.com/1.jpg");
        Product product2 = new Product(null, 2L, "Product 2", 75L, "Description 2", "http://example.com/2.jpg");

        entityManager.persist(product1);
        entityManager.persist(product2);

        List<Product> products = productRepository.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    void getProductById() {
        Product product = new Product(null, 1L, "Product 1", 50L, "Description 1", "http://example.com/1.jpg");
        entityManager.persist(product);

        Product retrievedProduct = productRepository.getProductById(product.getProductId());
        assertEquals("Product 1", retrievedProduct.getProductName());
    }

    @Test
    void getProductByName() {
        Product product = new Product(null, 1L, "Product 1", 50L, "Description 1", "http://example.com/1.jpg");
        entityManager.persist(product);

        Product retrievedProduct = productRepository.getProductByName("Product 1");
        assertEquals("Product 1", retrievedProduct.getProductName());
    }

    @Test
    void updateProduct() {
        Product product = new Product(null, 1L, "Product 1", 50L, "Description 1", "http://example.com/1.jpg");
        entityManager.persist(product);

        product.setProductName("Updated Product 1");
        productRepository.updateProduct(product);

        Product updatedProduct = entityManager.find(Product.class, product.getProductId());
        assertEquals("Updated Product 1", updatedProduct.getProductName());
    }

    @Test
    void deleteProductById() {
        Product product = new Product(null, 1L, "Product 1", 50L, "Description 1", "http://example.com/1.jpg");
        entityManager.persist(product);

        productRepository.deleteProductById(product.getProductId());

        Product deletedProduct = entityManager.find(Product.class, product.getProductId());
        assertNull(deletedProduct);
    }

    @Test
    void deleteProductByName() {
        Product product = new Product(null, 1L, "Product 1", 50L, "Description 1", "http://example.com/1.jpg");
        entityManager.persist(product);

        productRepository.deleteProductByName("Product 1");

        Product deletedProduct = entityManager.find(Product.class, product.getProductId());
        assertNull(deletedProduct);
    }
}