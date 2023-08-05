package basilium.basiliumspring.domain.question;

public class Question {
    Long questionId;
    String userId;
    Long productId;
    String question;
    String uploadDate;

    public Question(Long questionId, String userId, Long productId, String question, String uploadDate){
        this.questionId = questionId;
        this.userId = userId;
        this.productId = productId;
        this.question = question;
        this.uploadDate = uploadDate;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

}
