package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.repository.user.MemoryBrandUserRepository;
import basilium.basiliumspring.repository.user.MemoryNormalUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandUserServiceTest {

    BrandUserService brandUserService;
    MemoryBrandUserRepository brandUserRepository;

    @BeforeEach
    public void beforeEach(){
        brandUserRepository = new MemoryBrandUserRepository();
        brandUserService = new BrandUserService(brandUserRepository);
    }
    @AfterEach
    public void afterEach(){
        brandUserRepository.deleteAll();
    }

    @Test
    void join() {
        //given
        BrandUser brandUser = new BrandUser("aassdd123", "1234", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin","donggu", "01012345234", "www.basilium.com");
        //when
        boolean ret = brandUserService.join(brandUser);
        //then
        Assertions.assertThat(ret).isEqualTo(false);
        Assertions.assertThat(brandUserRepository.findById("aassdd123").isEmpty()).isEqualTo(true);

        //given
        BrandUser brandUser1 = new BrandUser("aassdd123", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin","donggu", "01012345234", "www.basilium.com");
        BrandUser brandUser2 = new BrandUser("aassdd124", "Aassdd1234", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin","donggu", "01012345234", "www.basilium.com");

        //when
        boolean ret1 = brandUserService.join(brandUser1);
        boolean ret2 = brandUserService.join(brandUser2);

        //then
        Assertions.assertThat(brandUserRepository.findById(brandUser1.getId()).get()).isEqualTo(brandUser1);
        Assertions.assertThat(ret2).isEqualTo(false);

        //given
        BrandUser brandUser3= new BrandUser("aassdd124", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin","donggu", "01012345234", "www.basilium.com");
        BrandUser brandUser4 = new BrandUser("aassdd124", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin","donggu", "01012345234", "www.basilium.com");

        //when
        boolean ret3 = brandUserService.join(brandUser3);
        boolean ret4 = brandUserService.join(brandUser4);

        //then
        Assertions.assertThat(brandUserRepository.findById(brandUser3.getId()).get()).isEqualTo(brandUser3);
        Assertions.assertThat(ret4).isEqualTo(false);
    }

}