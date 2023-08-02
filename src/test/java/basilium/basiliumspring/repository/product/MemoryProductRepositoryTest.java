package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

class MemoryProductRepositoryTest {
        private MemoryProductRepository memoryProductRepository;

        @BeforeEach
        void setUp() {
            memoryProductRepository = new MemoryProductRepository();
        }

        @Test
        void addProduct() {
            Product product = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http");
            memoryProductRepository.addProduct(product);

            List<Product> products = memoryProductRepository.getAllProducts();
            assertTrue(products.contains(product));
        }

        @Test
        void getAllProducts() {
            Product product1 = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http"); //pCI는 카테고리에서 string 상품에서 long으로 매핑 연결됨
            Product product2 = new Product(3L, 4L,"product B",88000L,"상품은 ~이렇습니다","http");

            memoryProductRepository.addProduct(product1);
            memoryProductRepository.addProduct(product2);

            List<Product> products = memoryProductRepository.getAllProducts();
            assertTrue(products.contains(product1));
            assertTrue(products.contains(product2));
        }

        @Test
        void getProductById() { //id로 상품 조회
            // Given
            Product product1 = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http");
            Product product2 = new Product(3L, 4L,"product B",88000L,"상품은 ~이렇습니다","http");

            // When
            memoryProductRepository.addProduct(product1);
            memoryProductRepository.addProduct(product2);

            // Then
            Product foundProduct1 = memoryProductRepository.getProductById(product1.getProductId());
            Product foundProduct2 = memoryProductRepository.getProductById(product2.getProductId());

            assertNotNull(foundProduct1);
            Assertions.assertThat(foundProduct1).isEqualTo(product1);

            assertNotNull(foundProduct2);
            Assertions.assertThat(foundProduct2).isEqualTo(product2);
        }

    @Test
    void getProductByName_ExistingProduct() { //이미 추가된 상품 name으로 해당 상품을 조회
        // Given
        Product product1 = new Product(1L, 2L, "product A", 59000L, "상품은 ~이렇습니다", "http");
        Product product2 = new Product(3L, 4L, "product B", 88000L, "상품은 ~이렇습니다", "http");

        memoryProductRepository.addProduct(product1);
        memoryProductRepository.addProduct(product2);

        // When
        Product foundProduct1 = memoryProductRepository.getProductByName("product A");
        Product foundProduct2 = memoryProductRepository.getProductByName("product B");

        // Then
        assertNotNull(foundProduct1);
        Assertions.assertThat(foundProduct1).isEqualTo(product1);

        assertNotNull(foundProduct2);
        Assertions.assertThat(foundProduct2).isEqualTo(product2);
    }

    @Test
    void getProductByName_NonExistingProduct() { //존재하지 않는 상품 이름으로 조회
        // Given
        Product product1 = new Product(1L, 2L, "product A", 59000L, "상품은 ~이렇습니다", "http");
        Product product2 = new Product(3L, 4L, "product B", 88000L, "상품은 ~이렇습니다", "http");

        memoryProductRepository.addProduct(product1);
        memoryProductRepository.addProduct(product2);

        // When
        Product foundProduct = memoryProductRepository.getProductByName("Non-existing Product");

        // Then
        assertNull(foundProduct);
    }


    @Test
        void updateProduct() {
            Product product = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http");
            memoryProductRepository.addProduct(product);

            Product updatedProduct = new Product(1L, 2L,"product B",59000L,"상품은 ~이렇습니다","http");
            memoryProductRepository.updateProduct(updatedProduct);

            List<Product> products = memoryProductRepository.getAllProducts();
            assertTrue(products.contains(updatedProduct));
            assertEquals(updatedProduct, memoryProductRepository.getProductById(1L));
        }

        @Test
        void deleteProductById() {
            Product product = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http");
            memoryProductRepository.addProduct(product);

            memoryProductRepository.deleteProductById(1L);

            List<Product> products = memoryProductRepository.getAllProducts();
            assertFalse(products.contains(product));
            assertNull(memoryProductRepository.getProductById(1L));
        }

        @Test
        void deleteProductByName() {
            Product product1 = new Product(1L, 2L,"product A",59000L,"상품은 ~A 이렇습니다","http");
            Product product2 = new Product(3L, 4L,"product B",88000L,"상품은 ~B 이렇습니다","http");
            Product product3 = new Product(5L, 6L,"product A",98000L,"상품은 ~C 이렇습니다","http");

            memoryProductRepository.addProduct(product1);
            memoryProductRepository.addProduct(product2);
            memoryProductRepository.addProduct(product3);
            assertTrue(memoryProductRepository.getAllProducts().contains(product1));//1과 3이 존재해야됨
            assertTrue(memoryProductRepository.getAllProducts().contains(product3));

            memoryProductRepository.deleteProductByName("product A"); //A만 삭제

            List<Product> products = memoryProductRepository.getAllProducts();
            assertFalse(products.contains(product1)); //오류발생
            assertFalse(products.contains(product3));
            assertTrue(products.contains(product2));
        }

}

