package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.JoinStatus;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.repository.user.JpaNormalUserRepository;
import basilium.basiliumspring.repository.user.MemoryNormalUserRepository;
import basilium.basiliumspring.repository.user.NormalUserRepository;
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
class NormalUserServiceTest {

    @Autowired
    NormalUserService normalUserService;
    @Autowired
    NormalUserRepository normalUserRepository;


    @Test
    void join() {
        //given
        NormalUser normalUser = new NormalUser();
        normalUser.setId("aaassdd123");
        normalUser.setPassword("1234");
        normalUser.setEmailAddress("aassdd123@gmail.com");
        normalUser.setPhoneNumber("01099999999");
        normalUser.setUserGrade(Grade.BRONZE);
        normalUser.setName("taemin");
        normalUser.setAge(24L);
        normalUser.setAddress("donggu");

        //when
        JoinStatus ret = normalUserService.join(normalUser);
        //then
        Assertions.assertThat(ret).isEqualTo(JoinStatus.INVALID_PASSWORD_LENGTH);
        Assertions.assertThat(normalUserRepository.findById("aaassdd123").isEmpty()).isEqualTo(true);

        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("aassdd123");
        normalUser1.setPassword("Asdf1234!");
        normalUser1.setEmailAddress("aassdd123@gmail.com");
        normalUser1.setPhoneNumber("01099999999");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("taemin");
        normalUser1.setAge(24L);
        normalUser1.setAddress("donggu");

        NormalUser normalUser2 = new NormalUser();
        normalUser2.setId("aassdd123");
        normalUser2.setPassword("1234");
        normalUser2.setEmailAddress("aassdd123@gmail.com");
        normalUser2.setPhoneNumber("01099999999");
        normalUser2.setUserGrade(Grade.BRONZE);
        normalUser2.setName("taemin");
        normalUser2.setAge(24L);
        normalUser2.setAddress("donggu");

        //when
        JoinStatus ret1 = normalUserService.join(normalUser1);
        JoinStatus ret2 = normalUserService.join(normalUser2);

        //then
        Assertions.assertThat(normalUserRepository.findById(normalUser1.getId()).get().getId()).isEqualTo(normalUser1.getId());
        Assertions.assertThat(ret2).isEqualTo(JoinStatus.DUPLICATE);

        //given
        NormalUser normalUser3 = new NormalUser();
        normalUser3.setId("baassdd123");
        normalUser3.setPassword("Asdf1234!");
        normalUser3.setEmailAddress("aassdd123@gmail.com");
        normalUser3.setPhoneNumber("01099999999");
        normalUser3.setUserGrade(Grade.BRONZE);
        normalUser3.setName("taemin");
        normalUser3.setAge(24L);
        normalUser3.setAddress("donggu");

        NormalUser normalUser4 = new NormalUser();
        normalUser4.setId("caassdd123");
        normalUser4.setPassword("Asdfg1234");
        normalUser4.setEmailAddress("aassdd123@gmail.com");
        normalUser4.setPhoneNumber("01099999999");
        normalUser4.setUserGrade(Grade.BRONZE);
        normalUser4.setName("taemin");
        normalUser4.setAge(24L);
        normalUser4.setAddress("donggu");

        //when
        JoinStatus ret3 = normalUserService.join(normalUser3);
        JoinStatus ret4 = normalUserService.join(normalUser4);

        //then
        Assertions.assertThat(normalUserRepository.findById(normalUser3.getId()).get().getId()).isEqualTo(normalUser3.getId());
        Assertions.assertThat(ret4).isEqualTo(JoinStatus.INVALID_PASSWORD_STRENGTH);
    }
}