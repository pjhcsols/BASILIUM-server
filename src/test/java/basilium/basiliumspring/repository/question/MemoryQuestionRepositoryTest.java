package basilium.basiliumspring.repository.question;

import basilium.basiliumspring.domain.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryQuestionRepositoryTest {

    private MemoryQuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        questionRepository = new MemoryQuestionRepository();
    }

    @Test
    void save() {
        // Given
        Question question = new Question(1L, "user1", 101L, "Is this product available in different colors?", "2023-08-06");

        // When
        questionRepository.save(question);

        // Then
        Question savedQuestion = questionRepository.findById(1L);
        assertNotNull(savedQuestion);
        assertEquals(question.getUserId(), savedQuestion.getUserId());
        assertEquals(question.getProductId(), savedQuestion.getProductId());
        assertEquals(question.getQuestion(), savedQuestion.getQuestion());
        assertEquals(question.getUploadDate(), savedQuestion.getUploadDate());
    }

    @Test
    void findById() {
        // Given
        Question question1 = new Question(1L, "user1", 101L, "Is this product available in different colors?", "2023-08-06");
        questionRepository.save(question1);

        // When
        Question foundQuestion = questionRepository.findById(1L);

        // Then
        assertNotNull(foundQuestion);
        assertEquals(question1.getQuestionId(), foundQuestion.getQuestionId());
        assertEquals(question1.getUserId(), foundQuestion.getUserId());
        assertEquals(question1.getProductId(), foundQuestion.getProductId());
        assertEquals(question1.getQuestion(), foundQuestion.getQuestion());
        assertEquals(question1.getUploadDate(), foundQuestion.getUploadDate());
    }

    @Test
    void findByProductId() {
        // Given
        Question question1 = new Question(1L, "user1", 101L, "Is this product available in different colors?", "2023-08-06");
        Question question2 = new Question(2L, "user2", 101L, "What material is this product made of?", "2023-08-07");
        Question question3 = new Question(3L, "user3", 102L, "Does this come in larger sizes?", "2023-08-08");
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        // When
        List<Question> questionsForProduct101 = questionRepository.findByProductId(101L);

        // Then
        assertEquals(2, questionsForProduct101.size());
        assertEquals("user1", questionsForProduct101.get(0).getUserId());
        assertEquals("user2", questionsForProduct101.get(1).getUserId());
    }

    @Test
    void findByUserId() {
        // Given
        Question question1 = new Question(1L, "user1", 101L, "Is this product available in different colors?", "2023-08-06");
        Question question2 = new Question(2L, "user1", 102L, "What material is this product made of?", "2023-08-07");
        Question question3 = new Question(3L, "user2", 103L, "Does this come in larger sizes?", "2023-08-08");
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        // When
        List<Question> questionsForUser1 = questionRepository.findByUserId("user1");

        // Then
        assertEquals(2, questionsForUser1.size());
        assertEquals(101L, questionsForUser1.get(0).getProductId());
        assertEquals(102L, questionsForUser1.get(1).getProductId());
    }

    @Test
    void update() {
        // Given
        Question question = new Question(1L, "user1", 101L, "Is this product available in different colors?", "2023-08-06");
        questionRepository.save(question);

        // When
        question.setQuestion("Is this product available in blue color?");
        questionRepository.update(question);

        // Then
        Question updatedQuestion = questionRepository.findById(1L);
        assertEquals("Is this product available in blue color?", updatedQuestion.getQuestion());
    }

    @Test
    void delete() {
        // Given
        Question question = new Question(1L, "user1", 101L, "Is this product available in different colors?", "2023-08-06");
        questionRepository.save(question);

        // When
        questionRepository.delete(1L);

        // Then
        Question deletedQuestion = questionRepository.findById(1L);
        assertNull(deletedQuestion);
    }

    @Test
    void findAll() {
        // Given
        Question question1 = new Question(1L, "user1", 101L, "Is this product available in different colors?", "2023-08-06");
        Question question2 = new Question(2L, "user2", 102L, "What material is this product made of?", "2023-08-07");
        questionRepository.save(question1);
        questionRepository.save(question2);

        // When
        List<Question> allQuestions = questionRepository.findAll();

        // Then
        assertEquals(2, allQuestions.size());
    }
}