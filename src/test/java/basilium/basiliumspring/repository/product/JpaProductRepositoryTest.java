package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @AfterEach
    public void afterEach(){
        productRepository.deleteAll();
    }
    @Test
    void addProduct() {
        //given
        Product product = new Product();


        product.setProductId(null);
        product.setProductCategoryId(1L);
        product.setProductName("Test Product");
        product.setProductPrice(100L);
        product.setProductDesc("Test Description");
        product.setProductPhotoUrl("http://example.com/test.jpg");



        //when
        productRepository.addProduct(product);
        //then
        Assertions.assertThat(productRepository.getProductById(product.getProductId()).get().getProductId()).isEqualTo(product.getProductId());
    }

    @Test
    void getAllProducts() {
        Product product1 = new Product();
        product1.setProductId(null);
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product1");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description1");
        product1.setProductPhotoUrl("http://example.com/test1.jpg");

        Product product2 = new Product();
        product2.setProductId(null);
        product2.setProductCategoryId(2L);
        product2.setProductName("Test Product2");
        product2.setProductPrice(100L);
        product2.setProductDesc("Test Description2");
        product2.setProductPhotoUrl("http://example.com/test2.jpg");
        productRepository.addProduct(product1);
        productRepository.addProduct(product2);

        List<Product> products = productRepository.getAllProducts();
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void getProductById() {
        Product product1 = new Product();
        product1.setProductId(null);
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description");
        product1.setProductPhotoUrl("http://example.com/test.jpg");
        productRepository.addProduct(product1);

        Assertions.assertThat(productRepository.getProductById(product1.getProductId()).get().getProductPrice()).isEqualTo(product1.getProductPrice());
    }

    @Test
    void getProductByName() {
        Product product1 = new Product();
        product1.setProductId(null);
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description");
        product1.setProductPhotoUrl("http://example.com/test.jpg");

        productRepository.addProduct(product1);

        Assertions.assertThat(productRepository.getProductByName(product1.getProductName()).get().getProductDesc()).isEqualTo(product1.getProductDesc());
    }

    @Test
    void updateProduct() {
        Product product1 = new Product();
        product1.setProductId(null);
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description");
        product1.setProductPhotoUrl("http://example.com/test.jpg");

        productRepository.addProduct(product1);
        product1.setProductName("product2");

        productRepository.updateProduct(product1);

        Assertions.assertThat(productRepository.getProductById(product1.getProductId()).get().getProductName()).isEqualTo(product1.getProductName());
    }

    @Test
    void deleteProductById() {
        Product product1 = new Product();
        product1.setProductId(null);
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description");
        product1.setProductPhotoUrl("http://example.com/test.jpg");
        productRepository.addProduct(product1);

        productRepository.deleteProductById(product1.getProductId());

        Assertions.assertThat(productRepository.getProductById(product1.getProductId()).isEmpty()).isEqualTo(true);
    }

    @Test
    void deleteProductByName() {
        Product product1 = new Product();
        product1.setProductId(null);
        product1.setProductCategoryId(1L);
        product1.setProductName("Test Product");
        product1.setProductPrice(100L);
        product1.setProductDesc("Test Description");
        product1.setProductPhotoUrl("http://example.com/test.jpg");

        productRepository.addProduct(product1);

        productRepository.deleteProductByName(product1.getProductName());

        Assertions.assertThat(productRepository.getProductById(product1.getProductId()).isEmpty()).isEqualTo(true);
    }

}