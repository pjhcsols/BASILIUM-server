package basilium.basiliumspring.repository.answer;

import basilium.basiliumspring.domain.answer.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    public Optional<Answer> getAnswerByAnswerId(Long answerId);
    public List<Answer> getAnswerByUserId(String userId);
    public List<Answer> getAnswerByProductId(Long productId);
    public List<Answer> getAnswerByUploadDate(String uploadDate);
    public Answer saveAnswer(Answer answer);
    public Answer modifyAnswer(Answer answer);

}
