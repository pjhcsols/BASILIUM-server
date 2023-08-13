package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.JoinStatus;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.domain.user.SuperUser;
import basilium.basiliumspring.repository.user.JpaSuperUserRepository;
import basilium.basiliumspring.repository.user.MemoryNormalUserRepository;
import basilium.basiliumspring.repository.user.MemorySuperUserRepository;
import basilium.basiliumspring.repository.user.SuperUserRepository;
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
class SuperUserServiceTest {
    @Autowired
    SuperUserService superUserService;
    @Autowired
    SuperUserRepository superUserRepository;
    @Test
    void join() {
        //given
        SuperUser superUser = new SuperUser();
        superUser.setId("aassdd123");
        superUser.setPassword("A34!");
        superUser.setEmailAddress("aassdd123@gmail.com");
        superUser.setPhoneNumber("01099999999");
        superUser.setUserGrade(Grade.BRONZE);

        //when
        JoinStatus ret = superUserService.join(superUser);
        //then
        Assertions.assertThat(ret).isEqualTo(JoinStatus.INVALID_PASSWORD_LENGTH);
        Assertions.assertThat(superUserRepository.findById("aassdd123").isEmpty()).isEqualTo(true);

        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("aaassdd123");
        superUser1.setPassword("Aassdd1234!");
        superUser1.setEmailAddress("aassdd123@gmail.com");
        superUser1.setPhoneNumber("01099999999");
        superUser1.setUserGrade(Grade.BRONZE);

        SuperUser superUser2 = new SuperUser();
        superUser2.setId("aaassdd123");
        superUser2.setPassword("aasd");
        superUser2.setEmailAddress("aassdd123@gmail.com");
        superUser2.setPhoneNumber("01099999999");
        superUser2.setUserGrade(Grade.BRONZE);

        //when
        JoinStatus ret1 = superUserService.join(superUser1);
        JoinStatus ret2 = superUserService.join(superUser2);

        //then
        Assertions.assertThat(superUserRepository.findById(superUser1.getId()).get().getEmailAddress()).isEqualTo(superUser1.getEmailAddress());
        Assertions.assertThat(ret2).isEqualTo(JoinStatus.DUPLICATE);

        //given
        SuperUser superUser3 = new SuperUser();
        superUser3.setId("caassdd123");
        superUser3.setPassword("Aassdd1234!");
        superUser3.setEmailAddress("aassdd123@gmail.com");
        superUser3.setPhoneNumber("01099999999");
        superUser3.setUserGrade(Grade.BRONZE);

        SuperUser superUser4 = new SuperUser();
        superUser4.setId("aassdd123");
        superUser4.setPassword("Aassdd1234");
        superUser4.setEmailAddress("aassdd123@gmail.com");
        superUser4.setPhoneNumber("01099999999");
        superUser4.setUserGrade(Grade.BRONZE);


        //when
        JoinStatus ret3 = superUserService.join(superUser3);
        JoinStatus ret4 = superUserService.join(superUser4);

        //then
        Assertions.assertThat(superUserRepository.findById(superUser3.getId()).get().getPhoneNumber()).isEqualTo(superUser3.getPhoneNumber());
        Assertions.assertThat(ret4).isEqualTo(JoinStatus.INVALID_PASSWORD_STRENGTH);
    }
}