package basilium.basiliumspring.repository.answer;

import basilium.basiliumspring.domain.answer.Answer;
import basilium.basiliumspring.repository.like.LikeRepository;
import basilium.basiliumspring.repository.like.MemoryLikeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryAnswerRepositoryTest {
    AnswerRepository repository = new MemoryAnswerRepository();

    @AfterEach
    public void afterEach(){
        repository = new MemoryAnswerRepository();
    }

    @Test
    void getAnswerByAnswerId() {
        //given
        Answer answer1 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        //when
        repository.saveAnswer(answer1);
        //then
        Assertions.assertThat(repository.getAnswerByAnswerId(answer1.getAnswerId()).get()).isEqualTo(answer1);
    }

    @Test
    void getAnswerByUserId() {
        //given
        Answer answer1 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        Answer answer2 = new Answer(2L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        //when
        repository.saveAnswer(answer1);
        repository.saveAnswer(answer2);
        //then
        Assertions.assertThat(repository.getAnswerByUserId(answer1.getUserId()).size()).isEqualTo(2);

    }

    @Test
    void getAnswerByProductId() {
        //given
        Answer answer1 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        Answer answer2 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        //when
        repository.saveAnswer(answer1);
        repository.saveAnswer(answer2);
        //then
        Assertions.assertThat(repository.getAnswerByProductId(answer1.getProductId()).size()).isEqualTo(2);
    }

    @Test
    void getAnswerByUploadDate() {
        //given
        Answer answer1 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        Answer answer2 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        //when
        repository.saveAnswer(answer1);
        repository.saveAnswer(answer2);
        //then
        Assertions.assertThat(repository.getAnswerByUploadDate(answer1.getUploadDate()).size()).isEqualTo(2);

    }

    @Test
    void saveAnswer() {
        //given
        Answer answer1 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        Answer answer2 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        //when
        repository.saveAnswer(answer1);
        repository.saveAnswer(answer2);
        //then
        Assertions.assertThat(repository.getAnswerByProductId(answer1.getProductId()).size()).isEqualTo(2);

    }

    @Test
    void modifyAnswer() {
        //given
        Answer answer1 = new Answer(1L, "seungjun", 1L, "어쩔티비", "2023-08-10");
        Answer answer2 = new Answer(1L, "jaejun", 1L, "어쩔티비", "2023-08-10");
        //when
        repository.saveAnswer(answer1);
        repository.modifyAnswer(answer2);
        //then
        Assertions.assertThat(repository.getAnswerByProductId(answer1.getProductId()).get(0).getUserId()).isEqualTo(answer2.getUserId());
    }
}