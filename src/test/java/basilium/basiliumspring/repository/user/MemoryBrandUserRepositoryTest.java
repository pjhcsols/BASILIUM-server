package basilium.basiliumspring.repository.user;

import basilium.basiliumspring.domain.user.BrandUser;
import basilium.basiliumspring.domain.user.Grade;
import basilium.basiliumspring.domain.user.NormalUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBrandUserRepositoryTest {

    MemoryBrandUserRepository repository = new MemoryBrandUserRepository();

    @AfterEach
    public void afterEach(){
        repository.deleteAll();
    }

    @Test
    void count() {
        //given
        BrandUser brandUser = new BrandUser("mzxcvver", "aaasdf23", "kimer@gmail.com", "0112345670", Grade.BRONZE, "kiJun", "Daegu", "000000000000", "www.basilium.com");
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
        BrandUser brandUser = new BrandUser("molover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        //when
        repository.save(brandUser);
        repository.delete(brandUser);
        //then
        repository.findById(brandUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        BrandUser brandUser2 = new BrandUser("olover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
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
        BrandUser brandUser = new BrandUser("molover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        //when
        repository.save(brandUser);
        repository.deleteById(brandUser.getId());
        //then
        repository.findById(brandUser.getId()).ifPresentOrElse(val -> {throw new IllegalStateException("데이터가 존재 하면 안됩니다.");}, () ->{
            System.out.println("정상적으로 동작합니다.");
        });

        //case2
        //given
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        BrandUser brandUser2 = new BrandUser("olover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
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
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        BrandUser brandUser2 = new BrandUser("olover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
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
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        //when
        repository.save(brandUser1);
        //then
        BrandUser result = repository.findById(brandUser1.getId()).get();
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findById() {
        //given
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        //when
        repository.save(brandUser1);
        BrandUser result = repository.findById(brandUser1.getId()).get();
        //then
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findByEmail() {
        //given
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        //when
        repository.save(brandUser1);
        BrandUser result = repository.findByEmail(brandUser1.getEmailAddress()).get();
        //then
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findByPhoneNumber() {
        //given
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        //when
        repository.save(brandUser1);
        BrandUser result = repository.findByPhoneNumber(brandUser1.getPhoneNumber()).get();
        //then
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findByName() {
        //given
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        //when
        repository.save(brandUser1);
        BrandUser result = repository.findByName(brandUser1.getFirmName()).get();
        //then
        Assertions.assertThat(result).isEqualTo(brandUser1);
    }

    @Test
    void findAll() {
        //given
        BrandUser brandUser1 = new BrandUser("moover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        BrandUser brandUser2 = new BrandUser("olover", "aassdd123", "kiokalover@gmail.com", "01088162470", Grade.BRONZE, "kem aeungJun", "daegu", "Deagu", "www.basilium.com");
        //when
        repository.save(brandUser1);
        repository.save(brandUser2);
        List<BrandUser> result = repository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}