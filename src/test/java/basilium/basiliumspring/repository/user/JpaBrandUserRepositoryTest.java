package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.service.user.BrandUserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@SpringBootTest
@Transactional
class JpaBrandUserRepositoryTest {

    @Qualifier("brandUserRepository")
    @Autowired
    BrandUserRepository repository;

    @AfterEach
    public void afterEach(){
        repository.deleteAll();
    }

    @Test
    void count() {
        //given
        BrandUser brandUser = new BrandUser();
        brandUser.setId("mzxcvver");
        brandUser.setPassword("aaasdf23");
        brandUser.setEmailAddress("kimer@gmail.com");
        brandUser.setPhoneNumber("0112345670");
        brandUser.setUserGrade(Grade.BRONZE);
        brandUser.setFirmName("kiJun");
        brandUser.setFirmAddress("Daegu");
        brandUser.setBusinessRegistration("000000000000");
        brandUser.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser);
        //then
        long result = repository.count();
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void delete() {
        //case1
        //given
        BrandUser brandUser = new BrandUser();
        brandUser.setId("mzxcvver");
        brandUser.setPassword("aaasdf23");
        brandUser.setEmailAddress("kimer@gmail.com");
        brandUser.setPhoneNumber("0112345670");
        brandUser.setUserGrade(Grade.BRONZE);
        brandUser.setFirmName("kiJun");
        brandUser.setFirmAddress("Daegu");
        brandUser.setBusinessRegistration("000000000000");
        brandUser.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser);
        repository.delete(brandUser);
        //then
        repository.findById(brandUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("mzxer");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        BrandUser brandUser2 = new BrandUser();
        brandUser2.setId("mzxvvr");
        brandUser2.setPassword("aaasdf23");
        brandUser2.setEmailAddress("kimer@gmail.com");
        brandUser2.setPhoneNumber("0112345670");
        brandUser2.setUserGrade(Grade.BRONZE);
        brandUser2.setFirmName("kiJun");
        brandUser2.setFirmAddress("Daegu");
        brandUser2.setBusinessRegistration("000000000000");
        brandUser2.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        repository.save(brandUser2);
        repository.delete(brandUser2);
        //then
        repository.findById(brandUser1.getId()).ifPresentOrElse(val -> {System.out.println("정상적으로 동작합니다.");}, () ->{
            throw new IllegalStateException("데이터가 존재 하지않으면 안됩니다.");
        });
    }

    @Test
    void deleteById() {
        //case1
        //given
        BrandUser brandUser = new BrandUser();
        brandUser.setId("mzxcvver");
        brandUser.setPassword("aaasdf23");
        brandUser.setEmailAddress("kimer@gmail.com");
        brandUser.setPhoneNumber("0112345670");
        brandUser.setUserGrade(Grade.BRONZE);
        brandUser.setFirmName("kiJun");
        brandUser.setFirmAddress("Daegu");
        brandUser.setBusinessRegistration("000000000000");
        brandUser.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser);
        repository.deleteById(brandUser.getId());
        //then
        repository.findById(brandUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("xcvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        BrandUser brandUser2 = new BrandUser();
        brandUser2.setId("mzxvasdfver");
        brandUser2.setPassword("aaasdf23");
        brandUser2.setEmailAddress("kimer@gmail.com");
        brandUser2.setPhoneNumber("0112345670");
        brandUser2.setUserGrade(Grade.BRONZE);
        brandUser2.setFirmName("kiJun");
        brandUser2.setFirmAddress("Daegu");
        brandUser2.setBusinessRegistration("000000000000");
        brandUser2.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        repository.save(brandUser2);
        repository.deleteById(brandUser2.getId());
        //then
        repository.findById(brandUser1.getId()).ifPresentOrElse(val -> {System.out.println("정상적으로 동작합니다.");}, () ->{
            throw new IllegalStateException("데이터가 존재 하지않으면 안됩니다.");
        });
    }

    @Test
    void deleteAll() {
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("amzxcvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        BrandUser brandUser2 = new BrandUser();
        brandUser2.setId("bmzxcvver");
        brandUser2.setPassword("aaasdf23");
        brandUser2.setEmailAddress("kimer@gmail.com");
        brandUser2.setPhoneNumber("0112345670");
        brandUser2.setUserGrade(Grade.BRONZE);
        brandUser2.setFirmName("kiJun");
        brandUser2.setFirmAddress("Daegu");
        brandUser2.setBusinessRegistration("000000000000");
        brandUser2.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        repository.save(brandUser2);
        repository.deleteAll();
        //then
        long result = repository.count();
        Assertions.assertThat(result).isEqualTo(0);

    }

    @Test
    void save() {
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("mzxcvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        //then
        BrandUser result = repository.findById(brandUser1.getId()).get();
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findById() {
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("mzxcvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        BrandUser result = repository.findById(brandUser1.getId()).get();
        //then
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findByEmail() {
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("mzxcvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        BrandUser result = repository.findByEmail(brandUser1.getEmailAddress()).get();
        //then
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findByPhoneNumber() {
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("mzxcvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        BrandUser result = repository.findByPhoneNumber(brandUser1.getPhoneNumber()).get();
        //then
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findByName() {
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("mzxcvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        BrandUser result = repository.findByName(brandUser1.getFirmName()).get();
        //then
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findAll() {
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("mzxcvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        BrandUser brandUser2 = new BrandUser();
        brandUser2.setId("amzxcvver");
        brandUser2.setPassword("aaasdf23");
        brandUser2.setEmailAddress("kimer@gmail.com");
        brandUser2.setPhoneNumber("0112345670");
        brandUser2.setUserGrade(Grade.BRONZE);
        brandUser2.setFirmName("kiJun");
        brandUser2.setFirmAddress("Daegu");
        brandUser2.setBusinessRegistration("000000000000");
        brandUser2.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        repository.save(brandUser2);
        List<BrandUser> result = repository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void modify() {
        //given
        BrandUser brandUser1 = new BrandUser();
        brandUser1.setId("mzxcvvver");
        brandUser1.setPassword("aaasdf23");
        brandUser1.setEmailAddress("kimer@gmail.com");
        brandUser1.setPhoneNumber("0112345670");
        brandUser1.setUserGrade(Grade.BRONZE);
        brandUser1.setFirmName("kiJun");
        brandUser1.setFirmAddress("Daegu");
        brandUser1.setBusinessRegistration("000000000000");
        brandUser1.setFirmWebUrl("www.basilium.com");
        BrandUser brandUser2 = new BrandUser();
        brandUser2.setId("mzxcvver");
        brandUser2.setPassword("aaasdf23");
        brandUser2.setEmailAddress("kimer@gmail.com");
        brandUser2.setPhoneNumber("0112345670");
        brandUser2.setUserGrade(Grade.BRONZE);
        brandUser2.setFirmName("kiJun");
        brandUser2.setFirmAddress("Daegu");
        brandUser2.setBusinessRegistration("000000000000");
        brandUser2.setFirmWebUrl("www.basilium.com");
        //when
        repository.save(brandUser1);
        repository.save(brandUser2);
        brandUser1.setPassword("asdf");
        repository.modify(brandUser1);
        //then
        BrandUser result = repository.findById(brandUser1.getId()).get();
        Assertions.assertThat(result.getPassword()).isEqualTo(brandUser1.getPassword());

    }
}