package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.NormalUser;
import basilium.basiliumspring.domain.user.SuperUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemorySuperUserRepositoryTest {
    MemorySuperUserRepository repository = new MemorySuperUserRepository();

    @AfterEach
    public void afterEach(){
        repository.deleteAll();
    }
    @Test
    void count() {
        //given
        SuperUser superUser = new SuperUser("mzxcvver", "aaasdf23", "kimer@gmail.com", "0112345670", Grade.DIAMOND);
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
        SuperUser superUser = new SuperUser("molover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.DIAMOND);
        //when
        repository.save(superUser);
        repository.delete(superUser);
        //then
        repository.findById(superUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        SuperUser superUser1 = new SuperUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.DIAMOND);
        SuperUser superUser2 = new SuperUser("aokalover", "aaerty23", "kasdfaasdfer@gmail.com", "010912370", Grade.DIAMOND);
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
        SuperUser superUser = new SuperUser("molover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.DIAMOND);
        //when
        repository.save(superUser);
        repository.deleteById(superUser.getId());
        //then
        repository.findById(superUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        SuperUser superUser1 = new SuperUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.DIAMOND);
        SuperUser superUser2 = new SuperUser("aokalover", "aaerty23", "kasdfaasdfer@gmail.com", "010912370", Grade.DIAMOND);
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
        SuperUser superUser1 = new SuperUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.DIAMOND);
        SuperUser superUser2 = new SuperUser("aokalover", "aaerty23", "kasdfaasdfer@gmail.com", "010912370", Grade.DIAMOND);
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
        SuperUser superUser1 = new SuperUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.DIAMOND);
        //when
        repository.save(superUser1);
        //then
        SuperUser result = repository.findById(superUser1.getId()).get();
        Assertions.assertThat(result).isEqualTo(superUser1);
    }

    @Test
    void findById() {
        //given
        SuperUser superUser1 = new SuperUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.DIAMOND);
        //when
        repository.save(superUser1);
        //then
        SuperUser result = repository.findById(superUser1.getId()).get();
        Assertions.assertThat(result).isEqualTo(superUser1);
    }

    @Test
    void findByEmail() {
        //given
        SuperUser superUser1 = new SuperUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.DIAMOND);
        //when
        repository.save(superUser1);
        //then
        SuperUser result = repository.findByEmail(superUser1.getEmailAddress()).get();
        Assertions.assertThat(result).isEqualTo(superUser1);
    }

    @Test
    void findByPhoneNumber() {
        //given
        SuperUser superUser1 = new SuperUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.DIAMOND);
        //when
        repository.save(superUser1);
        //then
        SuperUser result = repository.findByPhoneNumber(superUser1.getPhoneNumber()).get();
        Assertions.assertThat(result).isEqualTo(superUser1);
    }

    @Test
    void findAll() {
        //given
        SuperUser superUser1 = new SuperUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.DIAMOND);
        SuperUser superUser2 = new SuperUser("aokalover", "aaerty23", "kasdfaasdfer@gmail.com", "010912370", Grade.DIAMOND);
        //when
        repository.save(superUser1);
        repository.save(superUser2);
        List<SuperUser> result = repository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}