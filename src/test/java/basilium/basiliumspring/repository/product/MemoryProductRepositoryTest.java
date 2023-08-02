package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

// 카테고리name와 productCategoriId 연결
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
        void getProductById() {
            Product product1 = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http");
            Product product2 = new Product(3L, 4L,"product B",88000L,"상품은 ~이렇습니다","http");

            memoryProductRepository.addProduct(product1);
            memoryProductRepository.addProduct(product2);

            Product foundProduct1 = memoryProductRepository.getProductById(1L);
            Product foundProduct2 = memoryProductRepository.getProductById(3L);

            assertNotNull(foundProduct1);
            assertEquals(product1, foundProduct1);

            assertNotNull(foundProduct2); // 오류발생
            assertEquals(product2, foundProduct2);
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

