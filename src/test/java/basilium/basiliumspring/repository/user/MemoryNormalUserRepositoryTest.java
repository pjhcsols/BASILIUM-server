package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.NormalUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryNormalUserRepositoryTest {

    MemoryNormalUserRepository repository = new MemoryNormalUserRepository();

    @AfterEach
    public void afterEach(){
        repository.deleteAll();
    }

    @Test
    void count() {
        //given
        NormalUser normalUser = new NormalUser("mzxcvver", "aaasdf23", "kimer@gmail.com", "0112345670", Grade.BRONZE, "kiJun", 23L, "Deagu");
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
        NormalUser normalUser = new NormalUser("molover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", 23L, "Deagu");
        //when
        repository.save(normalUser);
        repository.delete(normalUser);
        //then
        repository.findById(normalUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        NormalUser normalUser2 = new NormalUser("aokalover", "aaerty23", "kasdfaasdfer@gmail.com", "010912370", Grade.BRONZE, "kn", 23L, "Deagu");
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
        NormalUser normalUser = new NormalUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", 23L, "Deagu");
        //when
        repository.save(normalUser);
        repository.deleteById(normalUser.getId());
        //then
        repository.findById(normalUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        NormalUser normalUser2 = new NormalUser("aokalover", "aaerty23", "kasdfaasdfer@gmail.com", "010912370", Grade.BRONZE, "kn", 23L, "Deagu");
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
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        NormalUser normalUser2 = new NormalUser("aokalover", "aaerty23", "kasdfaasdfer@gmail.com", "010912370", Grade.BRONZE, "kn", 23L, "Deagu");
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
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        //when
        repository.save(normalUser1);
        //then
        NormalUser result = repository.findById(normalUser1.getId()).get();
        Assertions.assertThat(result).isEqualTo(normalUser1);

    }

    @Test
    void findById() {
        //given
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        //when
        repository.save(normalUser1);
        NormalUser result = repository.findById(normalUser1.getId()).get();
        //then
        Assertions.assertThat(result).isEqualTo(normalUser1);
    }

    @Test
    void findByEmail() {
        //given
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        //when
        repository.save(normalUser1);
        NormalUser result = repository.findByEmail(normalUser1.getEmailAddress()).get();
        //then
        Assertions.assertThat(result).isEqualTo(normalUser1);
    }

    @Test
    void findByName() {
        //given
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        //when
        repository.save(normalUser1);
        NormalUser result = repository.findByName(normalUser1.getName()).get();
        //then
        Assertions.assertThat(result).isEqualTo(normalUser1);
    }

    @Test
    void findByPhoneNumber() {
        //given
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        //when
        repository.save(normalUser1);
        NormalUser result = repository.findByPhoneNumber(normalUser1.getPhoneNumber()).get();
        //then
        Assertions.assertThat(result).isEqualTo(normalUser1);
    }

    @Test
    void findAll() {
        //given
        NormalUser normalUser1 = new NormalUser("mxcvber", "aas3", "kimver@gmail.com", "01090", Grade.BRONZE, "kingJun", 23L, "Deagu");
        NormalUser normalUser2 = new NormalUser("aokalover", "aaerty23", "kasdfaasdfer@gmail.com", "010912370", Grade.BRONZE, "kn", 23L, "Deagu");
        repository.save(normalUser1);
        repository.save(normalUser2);
        //when
        List<NormalUser> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);

    }
}