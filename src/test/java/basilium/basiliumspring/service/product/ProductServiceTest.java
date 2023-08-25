package basilium.basiliumspring.service.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.repository.product.ProductRepository;
import basilium.basiliumspring.repository.user.NormalUserRepository;
import basilium.basiliumspring.service.product.ProductService;
import basilium.basiliumspring.service.user.NormalUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

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

@SpringBootTest
@Transactional
class ProductServiceTest {


}