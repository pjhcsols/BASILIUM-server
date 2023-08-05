package basilium.basiliumspring.repository.question;

import basilium.basiliumspring.domain.question.Question;

import java.util.List;

public interface QuestionRepository {
    // 질문을 저장하는 메서드
    void save(Question question);

    // questionId로 특정 질문을 조회하는 메서드
    Question findById(Long questionId);

    // productId로 해당 상품의 모든 질문을 조회하는 메서드
    List<Question> findByProductId(Long productId);

    // userId로 해당 사용자가 올린 모든 질문을 조회하는 메서드
    List<Question> findByUserId(String userId);

    // 질문을 수정하는 메서드
    void update(Question question);

    // 질문을 삭제하는 메서드
    void delete(Long questionId);

    // 모든 질문을 조회하는 메서드
    List<Question> findAll();
}
