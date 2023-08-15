package basilium.basiliumspring;

import basilium.basiliumspring.repository.user.*;
import basilium.basiliumspring.service.user.BrandUserService;
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
}
