package basilium.basiliumspring.service.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.repository.product.ProductRepository;
import basilium.basiliumspring.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given; // BDD  given 메서드를 사용하려면 해당 패키지를 임포트해야 합니다.


class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void addProduct() {
        // Given
        Product product = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http"); // 새 제품 생성

        // When
        productService.addProduct(product); // 제품 추가 메서드 호출

        // Then
        verify(productRepository, times(1)).addProduct(product); // 제품 추가가 올바르게 호출되었는지 검증
    }
    /*
    @Test
    void addProductAndMapToCategory() {
        // Given
        Product product = new Product(1L, 2L, "product A", 59000L, "상품은 ~이렇습니다", "http");
        Long categoryId = 2L;

        // When
        productService.addProductAndMapToCategory(product, categoryId);

        // Then
        verify(productRepository, times(1)).addProduct(product);
        verify(productRepository, times(1)).mapProductToCategory(product.getProductCategoryId(), categoryId);
    }
    */


    @Test
    void getAllProducts() {
        // Given
        List<Product> productList = new ArrayList<>();
        when(productRepository.getAllProducts()).thenReturn(productList); // 제품 목록 설정

        // When
        List<Product> result = productService.getAllProducts(); // 제품 목록 조회

        // Then
        assertEquals(productList, result); // 제품 목록이 올바르게 반환되는지 검증
    }

    @Test
    void getProductById() {
        // Given
        Long productId = 1L; // 특정 ID 설정
        Product product = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http"); // 가짜 제품 생성
        when(productRepository.getProductById(productId)).thenReturn(product); // 특정 ID의 제품 설정

        // When
        Product result = productService.getProductById(productId); // 특정 ID의 제품 조회

        // Then
        assertEquals(product, result); // 특정 ID의 제품이 올바르게 반환되는지 검증
    }

    @Test
    void updateProduct() {
        // Given
        Product product = new Product(1L, 2L,"product A",59000L,"상품은 ~이렇습니다","http"); // 새 제품 생성

        // When
        productService.updateProduct(product); // 제품 갱신 메서드 호출

        // Then
        verify(productRepository, times(1)).updateProduct(product); // 제품 갱신이 올바르게 호출되었는지 검증
    }

    @Test
    void deleteProductById() {
        // Given
        Long productId = 1L; // 특정 ID 설정

        // When
        productService.deleteProductById(productId); // 특정 ID의 제품 삭제 메서드 호출

        // Then
        verify(productRepository, times(1)).deleteProductById(productId); // 제품 삭제가 올바르게 호출되었는지 검증
    }

    @Test
    void deleteProductByName() {
        // Given
        String productName = "SampleProduct"; // 특정 이름 설정

        // When
        productService.deleteProductByName(productName); // 특정 이름의 제품 삭제 메서드 호출

        // Then
        verify(productRepository, times(1)).deleteProductByName(productName); // 제품 삭제가 올바르게 호출되었는지 검증
    }
}