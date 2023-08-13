package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.SuperUser;
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
class JpaSuperUserRepositoryTest {

    @Qualifier("superUserRepository")
    @Autowired
    SuperUserRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository.deleteAll();
    }
    @Test
    void count() {
        //given
        SuperUser superUser = new SuperUser();
        superUser.setId("mzxcvver");
        superUser.setPassword("aaasdf23");
        superUser.setEmailAddress("kimer@gmail.com");
        superUser.setPhoneNumber("0112345670");
        superUser.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser);
        //then
        long result = repository.count();
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void delete() {
        //case1
        //given
        SuperUser superUser = new SuperUser();
        superUser.setId("mzxcvver");
        superUser.setPassword("aaasdf23");
        superUser.setEmailAddress("kimer@gmail.com");
        superUser.setPhoneNumber("0112345670");
        superUser.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser);
        repository.delete(superUser);
        //then
        repository.findById(superUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("amzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        SuperUser superUser2 = new SuperUser();
        superUser2.setId("bmzxcvver");
        superUser2.setPassword("aaasdf23");
        superUser2.setEmailAddress("kimer@gmail.com");
        superUser2.setPhoneNumber("0112345670");
        superUser2.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser1);
        repository.save(superUser2);
        repository.delete(superUser2);
        //then
        repository.findById(superUser1.getId()).ifPresentOrElse(val -> {System.out.println("정상적으로 동작합니다.");}, () ->{
            throw new IllegalStateException("데이터가 존재 하지않으면 안됩니다.");
        });
    }

    @Test
    void deleteById() {
        //case1
        //given
        SuperUser superUser = new SuperUser();
        superUser.setId("mzxcvver");
        superUser.setPassword("aaasdf23");
        superUser.setEmailAddress("kimer@gmail.com");
        superUser.setPhoneNumber("0112345670");
        superUser.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser);
        repository.deleteById(superUser.getId());
        //then
        repository.findById(superUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("amzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        SuperUser superUser2 = new SuperUser();
        superUser2.setId("mzxcvver");
        superUser2.setPassword("aaasdf23");
        superUser2.setEmailAddress("kimer@gmail.com");
        superUser2.setPhoneNumber("0112345670");
        superUser2.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser1);
        repository.save(superUser2);
        repository.deleteById(superUser2.getId());
        //then
        repository.findById(superUser1.getId()).ifPresentOrElse(val -> {System.out.println("정상적으로 동작합니다.");}, () ->{
            throw new IllegalStateException("데이터가 존재 하지않으면 안됩니다.");
        });

    }

    @Test
    void deleteAll() {
        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("mzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        SuperUser superUser2 = new SuperUser();
        superUser2.setId("amzxcvver");
        superUser2.setPassword("aaasdf23");
        superUser2.setEmailAddress("kimer@gmail.com");
        superUser2.setPhoneNumber("0112345670");
        superUser2.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser1);
        repository.save(superUser2);
        repository.deleteAll();
        //then
        long result = repository.count();
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    void save() {
        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("mzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser1);
        //then
        SuperUser result = repository.findById(superUser1.getId()).get();
        Assertions.assertThat(result).isEqualTo(superUser1);
    }

    @Test
    void findById() {
        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("mzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser1);
        //then
        SuperUser result = repository.findById(superUser1.getId()).get();
        Assertions.assertThat(result).isEqualTo(superUser1);
    }

    @Test
    void findByEmail() {
        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("mzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser1);
        //then
        SuperUser result = repository.findByEmail(superUser1.getEmailAddress()).get();
        Assertions.assertThat(result).isEqualTo(superUser1);
    }

    @Test
    void findByPhoneNumber() {
        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("mzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser1);
        //then
        SuperUser result = repository.findByPhoneNumber(superUser1.getPhoneNumber()).get();
        Assertions.assertThat(result).isEqualTo(superUser1);
    }

    @Test
    void findAll() {
        //given
        SuperUser superUser1 = new SuperUser();
        superUser1.setId("mzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        SuperUser superUser2 = new SuperUser();
        superUser2.setId("amzxcvver");
        superUser2.setPassword("aaasdf23");
        superUser2.setEmailAddress("kimer@gmail.com");
        superUser2.setPhoneNumber("0112345670");
        superUser2.setUserGrade(Grade.DIAMOND);
        //when
        repository.save(superUser1);
        repository.save(superUser2);
        List<SuperUser> result = repository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void modify() {
        //given
        SuperUser superUser1 =  new SuperUser();
        superUser1.setId("mmzxcvver");
        superUser1.setPassword("aaasdf23");
        superUser1.setEmailAddress("kimer@gmail.com");
        superUser1.setPhoneNumber("0112345670");
        superUser1.setUserGrade(Grade.DIAMOND);
        SuperUser superUser2 =  new SuperUser();
        superUser2.setId("mzxcvver");
        superUser2.setPassword("aaasdf23");
        superUser2.setEmailAddress("kimer@gmail.com");
        superUser2.setPhoneNumber("0112345670");
        superUser2.setUserGrade(Grade.DIAMOND);

        repository.save(superUser1);
        repository.save(superUser2);
        //when
        superUser1.setPassword("asdf");
        repository.modify(superUser1);

        //then
        SuperUser result = repository.findById(superUser1.getId()).get();
        Assertions.assertThat(result.getPassword()).isEqualTo("asdf");
    }
}