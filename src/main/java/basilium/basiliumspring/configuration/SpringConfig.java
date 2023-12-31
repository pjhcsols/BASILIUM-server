package basilium.basiliumspring.configuration;

import basilium.basiliumspring.repository.like.JpaLikeRepository;
import basilium.basiliumspring.repository.like.LikeRepository;
import basilium.basiliumspring.repository.product.*;
import basilium.basiliumspring.repository.purchasetransaction.JpaPurchaseTransactionRepository;
import basilium.basiliumspring.repository.purchasetransaction.PurchaseTransactionRepository;
import basilium.basiliumspring.repository.review.JpaReviewRepository;
import basilium.basiliumspring.repository.review.ReviewRepository;
import basilium.basiliumspring.repository.shoppingcart.JpaShoppingCartRepository;
import basilium.basiliumspring.repository.shoppingcart.ShoppingCartRepository;
import basilium.basiliumspring.repository.user.*;
import basilium.basiliumspring.service.like.LikeService;
import basilium.basiliumspring.service.product.ProductService;
import basilium.basiliumspring.service.purchasetransaction.PurchaseTransactionService;
import basilium.basiliumspring.service.review.ReviewService;
import basilium.basiliumspring.service.shoppingcart.ShoppingCartService;
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
    public ProductService productService(){return new ProductService(productRepository(), imageRepository());}
    @Bean
    public JpaProductRepositoryLegend jpaProductRepositoryLegend(){return new JpaProductRepositoryLegend(em);}
    @Bean
    public ProductRepository productRepository(){return new JpaProductRepository(em, jpaProductRepositoryLegend());}
    @Bean
    public ReviewRepository reviewRepository(){return new JpaReviewRepository(em);}
    @Bean
    public ReviewService reviewService() {
        return new ReviewService(reviewRepository(), imageRepository());
    }

    @Bean
    public KakaoService kakaoService(){return new KakaoService();}

    @Bean
    public ImageRepository imageRepository(){return new JpaImageRepository(em);}

    @Bean
    public LikeService likeService(){return new LikeService(likeRepository());}
    @Bean
    public LikeRepository likeRepository(){return new JpaLikeRepository(em);}

    @Bean
    public PurchaseTransactionService purchaseTransactionService(){return new PurchaseTransactionService(purchaseTransactionRepository());}
    @Bean
    public PurchaseTransactionRepository purchaseTransactionRepository(){return new JpaPurchaseTransactionRepository(em);}

    @Bean
    public ShoppingCartService shoppingCartService(){return new ShoppingCartService(shoppingCartRepository());}

    @Bean
    public ShoppingCartRepository shoppingCartRepository(){return new JpaShoppingCartRepository(em);}


}
