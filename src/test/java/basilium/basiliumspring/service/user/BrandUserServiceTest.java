package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.JoinStatus;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.repository.user.BrandUserRepository;
import basilium.basiliumspring.repository.user.JpaBrandUserRepository;
import basilium.basiliumspring.repository.user.MemoryBrandUserRepository;
import basilium.basiliumspring.repository.user.MemoryNormalUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BrandUserServiceTest {

    @Autowired
    BrandUserService brandUserService;
    @Autowired
    BrandUserRepository brandUserRepository;

    @Test
    void join() {
        //given
        BrandUser brandUser = new BrandUser();

        brandUser.setId("aassdd123");
        brandUser.setPassword("1234");
        brandUser.setEmailAddress("aassdd123@gmail.com");
        brandUser.setPhoneNumber("01099999999");
        brandUser.setUserGrade(Grade.BRONZE);
        brandUser.setFirmName("taemin");
        brandUser.setFirmAddress("donggu");
        brandUser.setPhoneNumber("01012345234");
        brandUser.setFirmWebUrl("www.basilium.com");

        //when
        JoinStatus ret = brandUserService.join(brandUser);
        //then
        Assertions.assertThat(ret).isEqualTo(JoinStatus.INVALID_PASSWORD_LENGTH);
        Assertions.assertThat(brandUserRepository.findById("aassdd123").isEmpty()).isEqualTo(true);

        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("aaassdd123");
        brandUser1.setPassword("Aassdd1234!");
        brandUser1.setEmailAddress("aassdd123@gmail.com");
        brandUser1.setPhoneNumber("01099999999");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("taemin");
        brandUser1.setFirmAddress("donggu");
        brandUser1.setPhoneNumber("01012345234");
        brandUser1.setFirmWebUrl("www.basilium.com");

        BrandUser brandUser2 = new BrandUser();
        brandUser2.setId("baassdd123");
        brandUser2.setPassword("asdf1234");
        brandUser2.setEmailAddress("aassdd123@gmail.com");
        brandUser2.setPhoneNumber("01099999999");
        brandUser2.setUserGrade(Grade.BRONZE);
        brandUser2.setFirmName("taemin");
        brandUser2.setFirmAddress("donggu");
        brandUser2.setPhoneNumber("01012345234");
        brandUser2.setFirmWebUrl("www.basilium.com");

        //when
        JoinStatus ret1 = brandUserService.join(brandUser1);
        JoinStatus ret2 = brandUserService.join(brandUser2);

        //then
        Assertions.assertThat(brandUserRepository.findById(brandUser1.getId()).get().getFirmName()).isEqualTo(brandUser1.getFirmName());
        Assertions.assertThat(ret2).isEqualTo(JoinStatus.INVALID_PASSWORD_STRENGTH);

        //given
        BrandUser brandUser3= new BrandUser();
        brandUser3.setId("asssdd123");
        brandUser3.setPassword("asdfF1234!");
        brandUser3.setEmailAddress("aassdd123@gmail.com");
        brandUser3.setPhoneNumber("01099999999");
        brandUser3.setUserGrade(Grade.BRONZE);
        brandUser3.setFirmName("taemin");
        brandUser3.setFirmAddress("donggu");
        brandUser3.setPhoneNumber("01012345234");
        brandUser3.setFirmWebUrl("www.basilium.com");

        BrandUser brandUser4 = new BrandUser();
        brandUser4.setId("aaassdd123");
        brandUser4.setPassword("Asdf1234!");
        brandUser4.setEmailAddress("aassdd123@gmail.com");
        brandUser4.setPhoneNumber("01099999999");
        brandUser4.setUserGrade(Grade.BRONZE);
        brandUser4.setFirmName("taemin");
        brandUser4.setFirmAddress("donggu");
        brandUser4.setPhoneNumber("01012345234");
        brandUser4.setFirmWebUrl("www.basilium.com");

        //when
        JoinStatus ret3 = brandUserService.join(brandUser3);
        JoinStatus ret4 = brandUserService.join(brandUser4);

        //then
        Assertions.assertThat(brandUserRepository.findById(brandUser3.getId()).get().getPassword()).isEqualTo(brandUser3.getPassword());
        Assertions.assertThat(ret4).isEqualTo(JoinStatus.DUPLICATE);
    }

}