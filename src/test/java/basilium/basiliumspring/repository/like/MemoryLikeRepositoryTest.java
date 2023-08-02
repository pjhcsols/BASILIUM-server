package basilium.basiliumspring.repository.like;

import basilium.basiliumspring.domain.like.Like;
import basilium.basiliumspring.repository.user.MemoryBrandUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryLikeRepositoryTest {

    LikeRepository repository = new MemoryLikeRepository();

    @AfterEach
    public void afterEach(){
        repository.deleteAll();
        repository = new MemoryLikeRepository();
    }

    @Test
    void countByProductId() {
        //given
        Like like1 = new Like(0L,"jaejun", 13L);
        Like like2 = new Like(0L,"jaejun", 13L);
        Like like3 = new Like(0L,"jaejun", 13L);
        Like like4 = new Like(0L,"jaejun", 13L);
        repository.doLike(like1);
        repository.doLike(like2);
        repository.doLike(like3);
        repository.doLike(like4);
        //when
        Long result = repository.countByProductId(13L);
        //then
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    void countByUserId() {
        //given
        Like like1 = new Like(0L,"jaejun", 13L);
        Like like2 = new Like(0L,"jaejun", 14L);
        Like like3 = new Like(0L,"jaejun", 15L);
        Like like4 = new Like(0L,"jaejun", 16L);
        repository.doLike(like1);
        repository.doLike(like2);
        repository.doLike(like3);
        repository.doLike(like4);
        //when
        Long result = repository.countByUserId("jaejun");

        //then
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    void doLike() {
        //given
        Like like1 = new Like(0L,"jaejun", 13L);
        Like like2 = new Like(0L,"jaejun", 14L);
        //when
        Like result1 = repository.doLike(like1);
        Like result2 = repository.doLike(like2);
        System.out.println("result1 의 " + result1.getId());
        System.out.println("result2 의 " + result2.getId());
        //then
        Assertions.assertThat(result1.getId()).isEqualTo(1L);
        Assertions.assertThat(repository.countByUserId("jaejun")).isEqualTo(2L);
    }

    @Test
    void unDoLike() {
        //given
        Like like1 = new Like(0L,"jaejun", 13L);
        //when
        Like result1 = repository.doLike(like1);
        Like result2 = repository.unDoLike(like1).get();
        Optional<Like> result3 = repository.unDoLike(like1);
        //then
        Assertions.assertThat(result1).isEqualTo(result2);
        Assertions.assertThat(result3.isPresent()).isEqualTo(false);
    }
}