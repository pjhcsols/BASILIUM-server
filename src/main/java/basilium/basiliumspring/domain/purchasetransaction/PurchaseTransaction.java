package basilium.basiliumspring.domain.purchasetransaction;

public class PurchaseTransaction {
    private Long transactionId;
    private String userId;
    private Long productId;
    private Long totalPrice;
    private Payment paymentType;
    private String purchaseTime;

    public PurchaseTransaction(Long transactionId, String userId, Long productId, Long totalPrice, Payment paymentType, String purchaseTime) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.purchaseTime = purchaseTime;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Payment getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Payment paymentType) {
        this.paymentType = paymentType;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
