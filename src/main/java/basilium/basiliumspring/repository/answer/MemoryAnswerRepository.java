package basilium.basiliumspring.repository.answer;

import basilium.basiliumspring.domain.answer.Answer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryAnswerRepository implements AnswerRepository{
    Map<Long, Answer> store = new HashMap<>();
    Long sequence = 0L;

    @Override
    public Optional<Answer> getAnswerByAnswerId(Long answerId) {
        return Optional.ofNullable(store.get(answerId));
    }

    @Override
    public List<Answer> getAnswerByUserId(String userId) {
        return store.values().stream().filter(m->m.getUserId().equals(userId)).collect(Collectors.toList());

    }

    @Override
    public List<Answer> getAnswerByProductId(Long productId) {
        return store.values().stream().filter(m->m.getProductId() == productId).collect(Collectors.toList());
    }

    @Override
    public List<Answer> getAnswerByUploadDate(String uploadDate) {
        return store.values().stream().filter(m->m.getUploadDate().equals(uploadDate)).collect(Collectors.toList());
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        answer.setAnswerId(++sequence);
        store.put(answer.getAnswerId(), answer);
        return answer;
    }

    @Override
    public Answer modifyAnswer(Answer answer) {
        store.replace(answer.getAnswerId(), answer);
        return answer;
    }
}
