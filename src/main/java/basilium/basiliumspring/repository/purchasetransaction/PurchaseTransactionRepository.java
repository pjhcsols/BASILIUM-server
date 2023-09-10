package basilium.basiliumspring.repository.purchasetransaction;

import basilium.basiliumspring.domain.purchasetransaction.PurchaseTransaction;
import basilium.basiliumspring.domain.user.BrandUser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PurchaseTransactionRepository {
    PurchaseTransaction createTransaction(PurchaseTransaction transaction);
    List<PurchaseTransaction> findByUserId(String userId);

    List<PurchaseTransaction> findByDate(Date date);
    Optional<PurchaseTransaction> findByTransactionId(Long id);

    void deleteByTransactionId(Long id);
    void deleteByUserId(String userId);
    void deleteAll();
    PurchaseTransaction updateTransaction(PurchaseTransaction transaction);
    List<PurchaseTransaction> getPurchaseTransactionByUserId(String userId);

    void deleteByUserIdAndProductId(String userId, Long productId);


}