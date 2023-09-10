package basilium.basiliumspring.repository.purchasetransaction;

import basilium.basiliumspring.domain.purchasetransaction.PurchaseTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JpaPurchaseTransactionRepository implements PurchaseTransactionRepository{

    private EntityManager em;

    public JpaPurchaseTransactionRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public PurchaseTransaction createTransaction(PurchaseTransaction transaction) {
        em.persist(transaction);
        return transaction;
    }

    @Override
    public List<PurchaseTransaction> findByUserId(String userId) {
        TypedQuery<PurchaseTransaction> query = em.createQuery("SELECT m FROM PurchaseTransaction m WHERE m.userId = :userId", PurchaseTransaction.class);
        query.setParameter("userId", userId);

        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            return Collections.emptyList(); // 결과가 없는 경우 빈 목록 반환
        }
    }

    @Override
    public List<PurchaseTransaction> findByDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date); // 날짜를 문자열로 변환

        TypedQuery<PurchaseTransaction> query = em.createQuery("SELECT m FROM PurchaseTransaction m WHERE DATE(m.purchaseTime) = :date", PurchaseTransaction.class);
        query.setParameter("date", dateString);

        return query.getResultList();
    }

    @Override
    public Optional<PurchaseTransaction> findByTransactionId(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteByTransactionId(Long id) {
        PurchaseTransaction purchaseTransaction = em.find(PurchaseTransaction.class, id);
        if (purchaseTransaction != null)em.remove(purchaseTransaction);
    }

    @Override
    public void deleteByUserId(String userId) {
        try {
            em.createQuery("DELETE FROM PurchaseTransaction m WHERE m.userId = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete purchase transactions for user ID: " + userId, ex);
        }
    }

    @Override
    public void deleteAll() {
        em.createQuery("delete from PurchaseTransaction ").executeUpdate();
    }

    @Override
    public PurchaseTransaction updateTransaction(PurchaseTransaction updatedTransaction) {
        // 업데이트할 엔티티를 데이터베이스에서 조회
        PurchaseTransaction existingTransaction = em.find(PurchaseTransaction.class, updatedTransaction.getTransactionId());

        if (existingTransaction == null) {
            throw new EntityNotFoundException("Transaction not found with ID: " + updatedTransaction.getTransactionId());
        }

        // 필요한 경우 엔티티의 모든 필드를 업데이트
        existingTransaction.setUserId(updatedTransaction.getUserId());
        existingTransaction.setProductId(updatedTransaction.getProductId());
        existingTransaction.setTotalPrice(updatedTransaction.getTotalPrice());
        existingTransaction.setPaymentType(updatedTransaction.getPaymentType());
        existingTransaction.setPurchaseTime(updatedTransaction.getPurchaseTime());
        // 기타 필드 업데이트

        // 업데이트된 엔티티를 저장하여 변경 사항을 반영
        return em.merge(existingTransaction);
    }

    @Override
    public List<PurchaseTransaction> getPurchaseTransactionByUserId(String userId) {
        TypedQuery<PurchaseTransaction> result = em.createQuery("select m from PurchaseTransaction m where m.userId = :userId", PurchaseTransaction.class);
        result.setParameter("userId", userId);
        return result.getResultList();
    }

    @Override
    public void deleteByUserIdAndProductId(String userId, Long productId) {
        em.createQuery("delete from PurchaseTransaction m where m.userId = :userId and m.productId = :productId")
                .setParameter("userId", userId)
                .setParameter("productId", productId)
                .executeUpdate();
    }
}
