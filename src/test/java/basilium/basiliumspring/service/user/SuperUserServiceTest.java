package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.JoinStatus;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.domain.user.SuperUser;
import basilium.basiliumspring.repository.user.MemoryNormalUserRepository;
import basilium.basiliumspring.repository.user.MemorySuperUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperUserServiceTest {
    SuperUserService superUserService;
    MemorySuperUserRepository superUserRepository;

    @BeforeEach
    public void beforeEach(){
        superUserRepository = new MemorySuperUserRepository();
        superUserService = new SuperUserService(superUserRepository);
    }
    @AfterEach
    public void afterEach(){
        superUserRepository.deleteAll();
    }

    @Test
    void join() {
        //given
        SuperUser superUser = new SuperUser("aassdd123", "1234", "aassdd123@gmail.com", "01099999999", Grade.BRONZE);
        //when
        JoinStatus ret = superUserService.join(superUser);
        //then
        Assertions.assertThat(ret).isEqualTo(JoinStatus.INVALID_PASSWORD_LENGTH);
        Assertions.assertThat(superUserRepository.findById("aassdd123").isEmpty()).isEqualTo(true);

        //given
        SuperUser superUser1 = new SuperUser("aassdd123", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE);
        SuperUser superUser2 = new SuperUser("aassdd123", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE);

        //when
        JoinStatus ret1 = superUserService.join(superUser1);
        JoinStatus ret2 = superUserService.join(superUser2);

        //then
        Assertions.assertThat(superUserRepository.findById(superUser1.getId()).get()).isEqualTo(superUser1);
        Assertions.assertThat(ret2).isEqualTo(JoinStatus.DUPLICATE);

        //given
        SuperUser superUser3 = new SuperUser("aassdd124", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE);
        SuperUser superUser4 = new SuperUser("aassdd125", "Aassdd1234", "aassdd123@gmail.com", "01099999999", Grade.BRONZE);

        //when
        JoinStatus ret3 = superUserService.join(superUser3);
        JoinStatus ret4 = superUserService.join(superUser4);

        //then
        Assertions.assertThat(superUserRepository.findById(superUser3.getId()).get()).isEqualTo(superUser3);
        Assertions.assertThat(ret4).isEqualTo(JoinStatus.INVALID_PASSWORD_STRENGTH);
    }
}