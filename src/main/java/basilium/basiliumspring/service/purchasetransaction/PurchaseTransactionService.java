package basilium.basiliumspring.service.purchasetransaction;

import basilium.basiliumspring.domain.purchasetransaction.PurchaseTransaction;
import basilium.basiliumspring.repository.purchasetransaction.PurchaseTransactionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PurchaseTransactionService {
    private final PurchaseTransactionRepository purchaseTransactionRepository;

    public PurchaseTransactionService(PurchaseTransactionRepository purchaseTransactionRepository) {
        this.purchaseTransactionRepository = purchaseTransactionRepository;
    }

    public PurchaseTransaction savePurchaseTransaction(PurchaseTransaction purchaseTransaction){
        return purchaseTransactionRepository.createTransaction(purchaseTransaction);
    }

    public void deletePurchaseTransaction(String userId, Long productId){
        purchaseTransactionRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public PurchaseTransaction modifyPurchaseTransaction(PurchaseTransaction purchaseTransaction){
        return purchaseTransactionRepository.updateTransaction(purchaseTransaction);
    }

    public List<PurchaseTransaction> getPurchaseTransactionByUserId(String userId){
        return purchaseTransactionRepository.getPurchaseTransactionByUserId(userId);
    }
}
