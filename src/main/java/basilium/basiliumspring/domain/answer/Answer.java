package basilium.basiliumspring.domain.answer;

public class Answer {
    private Long answerId;
    private String userId;
    private Long productId;
    private String answer;
    private String uploadDate;

    public Answer(Long answerId, String userId, Long productId, String answer, String uploadDate) {
        this.answerId = answerId;
        this.userId = userId;
        this.productId = productId;
        this.answer = answer;
        this.uploadDate = uploadDate;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
