package basilium.basiliumspring.repository.question;

import basilium.basiliumspring.domain.question.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryQuestionRepository implements QuestionRepository{
    private final Map<Long, Question> questionMap;

    public MemoryQuestionRepository() {
        this.questionMap = new HashMap<>();
    }

    @Override
    public void save(Question question) {
        // 질문을 저장하는 메서드 구현
        questionMap.put(question.getQuestionId(), question);
    }

    @Override
    public Question findById(Long questionId) {
        // questionId로 특정 질문을 조회하는 메서드 구현
        return questionMap.get(questionId);
    }

    @Override
    public List<Question> findByProductId(Long productId) {
        // productId로 해당 상품의 모든 질문을 조회하는 메서드 구현
        List<Question> questionsByProductId = new ArrayList<>();
        for (Question question : questionMap.values()) {
            if (question.getProductId().equals(productId)) {
                questionsByProductId.add(question);
            }
        }
        return questionsByProductId;
    }

    @Override
    public List<Question> findByUserId(String userId) {
        // userId로 해당 사용자가 올린 모든 질문을 조회하는 메서드 구현
        List<Question> questionsByUserId = new ArrayList<>();
        for (Question question : questionMap.values()) {
            if (question.getUserId().equals(userId)) {
                questionsByUserId.add(question);
            }
        }
        return questionsByUserId;
    }

    @Override
    public void update(Question question) {
        // 기존 질문을 수정하는 메서드 구현
        if (questionMap.containsKey(question.getQuestionId())) {
            questionMap.put(question.getQuestionId(), question);
        }
    }

    @Override
    public void delete(Long questionId) {
        // 특정 질문을 questionId를 이용하여 삭제하는 메서드 구현
        questionMap.remove(questionId);
    }

    @Override
    public List<Question> findAll() {
        // 모든 질문을 조회하는 메서드 구현
        return new ArrayList<>(questionMap.values());
    }
}
