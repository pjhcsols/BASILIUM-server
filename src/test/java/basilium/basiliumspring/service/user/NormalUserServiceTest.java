package basilium.basiliumspring.service.user;

import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.repository.user.MemoryNormalUserRepository;
import basilium.basiliumspring.repository.user.NormalUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalUserServiceTest {

    NormalUserService normalUserService;
    MemoryNormalUserRepository normalUserRepository;

    @BeforeEach
    public void beforeEach(){
        normalUserRepository = new MemoryNormalUserRepository();
        normalUserService = new NormalUserService(normalUserRepository);
    }
    @AfterEach
    public void afterEach(){
        normalUserRepository.deleteAll();
    }

    @Test
    void join() {
        //given
        NormalUser normalUser = new NormalUser("aassdd123", "1234", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin", 24L, "donggu");
        //when
        boolean ret = normalUserService.join(normalUser);
        //then
        Assertions.assertThat(ret).isEqualTo(false);
        Assertions.assertThat(normalUserRepository.findById("aassdd123").isEmpty()).isEqualTo(true);

        //given
        NormalUser normalUser1 = new NormalUser("aassdd123", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin", 24L, "donggu");
        NormalUser normalUser2 = new NormalUser("aassdd123", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin", 24L, "donggu");

        //when
        boolean ret1 = normalUserService.join(normalUser1);
        boolean ret2 = normalUserService.join(normalUser2);

        //then
        Assertions.assertThat(normalUserRepository.findById(normalUser1.getId()).get()).isEqualTo(normalUser1);
        Assertions.assertThat(ret2).isEqualTo(false);

        //given
        NormalUser normalUser3 = new NormalUser("aassdd124", "Aassdd1234!", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin", 24L, "donggu");
        NormalUser normalUser4 = new NormalUser("aassdd125", "Aassdd1234", "aassdd123@gmail.com", "01099999999", Grade.BRONZE, "taemin", 24L, "donggu");

        //when
        boolean ret3 = normalUserService.join(normalUser3);
        boolean ret4 = normalUserService.join(normalUser4);

        //then
        Assertions.assertThat(normalUserRepository.findById(normalUser3.getId()).get()).isEqualTo(normalUser3);
        Assertions.assertThat(ret4).isEqualTo(false);
    }
}