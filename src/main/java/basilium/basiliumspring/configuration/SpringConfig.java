package basilium.basiliumspring.configuration;

import basilium.basiliumspring.repository.image.ImageRepository;
import basilium.basiliumspring.repository.image.JpaImageRepository;
import basilium.basiliumspring.repository.product.JpaProductRepository;
import basilium.basiliumspring.repository.product.ProductRepository;
import basilium.basiliumspring.repository.user.*;
import basilium.basiliumspring.service.image.ImageService;
import basilium.basiliumspring.service.product.ProductService;
import basilium.basiliumspring.service.user.BrandUserService;
import basilium.basiliumspring.service.user.KakaoService;
import basilium.basiliumspring.service.user.NormalUserService;
import basilium.basiliumspring.service.user.SuperUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    private final EntityManager em;
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    @Bean
    public NormalUserService normalUserService() {
        return new NormalUserService(normalUserRepository());
    }
    @Bean
    public NormalUserRepository normalUserRepository() {
        return new JpaNormalUserRepository(em);
    }
    @Bean
    public BrandUserService brandUserService() {
        return new BrandUserService(brandUserRepository());
    }
    @Bean
    public BrandUserRepository brandUserRepository() {
        return new JpaBrandUserRepository(em);
    }
    @Bean
    public SuperUserService superUserService() {
        return new SuperUserService(superUserRepository());
    }
    @Bean
    public SuperUserRepository superUserRepository() {
        return new JpaSuperUserRepository(em);
    }

    @Bean
    public ProductService productService(){return new ProductService(productRepository());}
    @Bean
    public ProductRepository productRepository(){return new JpaProductRepository(em);}

    @Bean
    public KakaoService kakaoService(){return new KakaoService();}

    @Bean
    public ImageRepository imageRepository(){return new JpaImageRepository(em);}

    @Bean
    public ImageService imageService(){return new ImageService(imageRepository());}


}
