package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.NormalUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
public class JpaNormalUserRepositoryTest {

    @Qualifier("normalUserRepository")
    @Autowired
    private NormalUserRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository.deleteAll();
    }

    @Test
    void count() {
        //given
        NormalUser normalUser = new NormalUser();
        normalUser.setId("mzxcvver");
        normalUser.setPassword("aaasdf23");
        normalUser.setEmailAddress("kimer@gmail.com");
        normalUser.setPhoneNumber("0112345670");
        normalUser.setUserGrade(Grade.BRONZE);
        normalUser.setName("kiJun");
        normalUser.setAge(23L);
        normalUser.setAddress("Deagu");
        //when
        repository.save(normalUser);
        //then
        long result = repository.count();
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void delete() {
        //case1
        //given
        NormalUser normalUser = new NormalUser();
        normalUser.setId("mzxcvver");
        normalUser.setPassword("aaasdf23");
        normalUser.setEmailAddress("kimer@gmail.com");
        normalUser.setPhoneNumber("0112345670");
        normalUser.setUserGrade(Grade.BRONZE);
        normalUser.setName("kiJun");
        normalUser.setAge(23L);
        normalUser.setAddress("Deagu");
        //when
        repository.save(normalUser);
        repository.delete(normalUser);
        //then
        repository.findById(normalUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        NormalUser normalUser2 = new NormalUser();
        normalUser2.setId("mzcvver");
        normalUser2.setPassword("aaasdf23");
        normalUser2.setEmailAddress("kimer@gmail.com");
        normalUser2.setPhoneNumber("0112345670");
        normalUser2.setUserGrade(Grade.BRONZE);
        normalUser2.setName("kiJun");
        normalUser2.setAge(23L);
        normalUser2.setAddress("Deagu");
        //when
        repository.save(normalUser1);
        repository.save(normalUser2);
        repository.delete(normalUser2);
        //then
        repository.findById(normalUser1.getId()).ifPresentOrElse(val -> {System.out.println("정상적으로 동작합니다.");}, () ->{
            throw new IllegalStateException("데이터가 존재 하지않으면 안됩니다.");
        });
    }

    @Test
    void deleteById() {
        //case1
        //given
        NormalUser normalUser = new NormalUser();
        normalUser.setId("mzxcvver");
        normalUser.setPassword("aaasdf23");
        normalUser.setEmailAddress("kimer@gmail.com");
        normalUser.setPhoneNumber("0112345670");
        normalUser.setUserGrade(Grade.BRONZE);
        normalUser.setName("kiJun");
        normalUser.setAge(23L);
        normalUser.setAddress("Deagu");
        //when
        repository.save(normalUser);
        repository.deleteById(normalUser.getId());
        //then
        repository.findById(normalUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        NormalUser normalUser2 = new NormalUser();
        normalUser2.setId("mcvver");
        normalUser2.setPassword("aaasdf23");
        normalUser2.setEmailAddress("kimer@gmail.com");
        normalUser2.setPhoneNumber("0112345670");
        normalUser2.setUserGrade(Grade.BRONZE);
        normalUser2.setName("kiJun");
        normalUser2.setAge(23L);
        normalUser2.setAddress("Deagu");
        //when
        repository.save(normalUser1);
        repository.save(normalUser2);
        repository.deleteById(normalUser2.getId());
        //then
        repository.findById(normalUser1.getId()).ifPresentOrElse(val -> {System.out.println("정상적으로 동작합니다.");}, () ->{
            throw new IllegalStateException("데이터가 존재 하지않으면 안됩니다.");
        });
    }

    @Test
    void deleteAll() {
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        NormalUser normalUser2 = new NormalUser();
        normalUser2.setId("mzvver");
        normalUser2.setPassword("aaasdf23");
        normalUser2.setEmailAddress("kimer@gmail.com");
        normalUser2.setPhoneNumber("0112345670");
        normalUser2.setUserGrade(Grade.BRONZE);
        normalUser2.setName("kiJun");
        normalUser2.setAge(23L);
        normalUser2.setAddress("Deagu");
        //when
        repository.save(normalUser1);
        repository.save(normalUser2);
        //then
        repository.deleteAll();
        long result = repository.count();
        Assertions.assertThat(result).isEqualTo(0);

    }

    @Test
    void save() {
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        //when
        repository.save(normalUser1);
        //then
        NormalUser result = repository.findById(normalUser1.getId()).get();
        Assertions.assertThat(result).isEqualTo(normalUser1);

    }

    @Test
    void findById() {
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        //when
        repository.save(normalUser1);
        NormalUser result = repository.findById(normalUser1.getId()).get();
        //then
        Assertions.assertThat(result).isEqualTo(normalUser1);
    }

    @Test
    void findByEmail() {
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        //when
        repository.save(normalUser1);
        NormalUser result = repository.findByEmail(normalUser1.getEmailAddress()).get();
        //then
        Assertions.assertThat(result).isEqualTo(normalUser1);
    }

    @Test
    void findByName() {
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        //when
        repository.save(normalUser1);
        NormalUser result = repository.findByName(normalUser1.getName()).get(0);
        //then
        Assertions.assertThat(result).isEqualTo(normalUser1);
    }

    @Test
    void findByPhoneNumber() {
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        //when
        repository.save(normalUser1);
        NormalUser result = repository.findByPhoneNumber(normalUser1.getPhoneNumber()).get();
        //then
        Assertions.assertThat(result).isEqualTo(normalUser1);
    }

    @Test
    void findAll() {
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        NormalUser normalUser2 = new NormalUser();
        normalUser2.setId("xcvver");
        normalUser2.setPassword("aaasdf23");
        normalUser2.setEmailAddress("kimer@gmail.com");
        normalUser2.setPhoneNumber("0112345670");
        normalUser2.setUserGrade(Grade.BRONZE);
        normalUser2.setName("kiJun");
        normalUser2.setAge(23L);
        normalUser2.setAddress("Deagu");
        repository.save(normalUser1);
        repository.save(normalUser2);
        //when
        List<NormalUser> result = repository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);

    }

    @Test
    void modify() {
        //given
        NormalUser normalUser1 = new NormalUser();
        normalUser1.setId("mzxcvver");
        normalUser1.setPassword("aaasdf23");
        normalUser1.setEmailAddress("kimer@gmail.com");
        normalUser1.setPhoneNumber("0112345670");
        normalUser1.setUserGrade(Grade.BRONZE);
        normalUser1.setName("kiJun");
        normalUser1.setAge(23L);
        normalUser1.setAddress("Deagu");
        NormalUser normalUser2 = new NormalUser();
        normalUser2.setId("mcvver");
        normalUser2.setPassword("aaasdf23");
        normalUser2.setEmailAddress("kimer@gmail.com");
        normalUser2.setPhoneNumber("0112345670");
        normalUser2.setUserGrade(Grade.BRONZE);
        normalUser2.setName("kiJun");
        normalUser2.setAge(23L);
        normalUser2.setAddress("Deagu");

        repository.save(normalUser1);
        repository.save(normalUser2);
        //when
        normalUser1.setPassword("asdf");
        repository.modify(normalUser1);

        //then
        NormalUser result = repository.findById(normalUser1.getId()).get();
        Assertions.assertThat(result.getPassword()).isEqualTo("asdf");
    }
}
