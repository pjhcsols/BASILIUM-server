package basilium.basiliumspring.repository.purchasetransaction;

import basilium.basiliumspring.domain.purchasetransaction.Payment;
import basilium.basiliumspring.domain.purchasetransaction.PurchaseTransaction;
import basilium.basiliumspring.repository.like.LikeRepository;
import basilium.basiliumspring.repository.like.MemoryLikeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPurchaseTransactionRepositoryTest {

    PurchaseTransactionRepository repository = new MemoryPurchaseTransactionRepository();


    @AfterEach
    void afterEach()
    {
        repository = new MemoryPurchaseTransactionRepository();
    }
    @Test
    void createTransaction() {
        //given
        PurchaseTransaction transaction1 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        PurchaseTransaction transaction2 = new PurchaseTransaction(1L, "taejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        //when
        repository.createTransaction(transaction1);
        repository.createTransaction(transaction2);
        //then
        Assertions.assertThat(repository.findByUserId(transaction1.getUserId()).get(0).getTransactionId()).isEqualTo(transaction1.getTransactionId());
        Assertions.assertThat(repository.findByUserId(transaction2.getUserId()).get(0).getTransactionId()).isEqualTo(transaction2.getTransactionId());

        Assertions.assertThat(repository.findByUserId(transaction1.getUserId()).get(0)).isEqualTo(transaction1);
        Assertions.assertThat(repository.findByUserId(transaction1.getUserId()).get(0)).isEqualTo(transaction1);

    }

    @Test
    void findByUserId() {
        //given
        PurchaseTransaction transaction1 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        //when
        repository.createTransaction(transaction1);
        //then
        Assertions.assertThat(repository.findByUserId(transaction1.getUserId()).get(0)).isEqualTo(transaction1);
    }

    @Test
    void findByDate() {
        //given
        PurchaseTransaction transaction1 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        //when
        repository.createTransaction(transaction1);
        //then
        Assertions.assertThat(repository.findByDate(transaction1.getPurchaseTime()).get(0)).isEqualTo(transaction1);
    }

    @Test
    void findByTransactionId() {
        //given
        PurchaseTransaction transaction1 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        //when
        repository.createTransaction(transaction1);
        //then
        Assertions.assertThat(repository.findByTransactionId(transaction1.getTransactionId()).get()).isEqualTo(transaction1);
    }

    @Test
    void deleteByTransactionId() {
        //given
        PurchaseTransaction transaction1 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        //when
        repository.createTransaction(transaction1);
        repository.deleteByTransactionId(transaction1.getTransactionId());
        //then

        Assertions.assertThat(repository.findByTransactionId(transaction1.getTransactionId()).isEmpty()).isEqualTo(true);
    }

    @Test
    void deleteByUserId() {
        //given
        PurchaseTransaction transaction1 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        //when
        repository.createTransaction(transaction1);
        repository.deleteByUserId(transaction1.getUserId());
        //then

        Assertions.assertThat(repository.findByUserId(transaction1.getUserId()).size()).isEqualTo(0);
    }

    @Test
    void deleteAll() {
        //given
        PurchaseTransaction transaction1 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        PurchaseTransaction transaction2 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        //when
        repository.createTransaction(transaction1);
        repository.createTransaction(transaction2);
        repository.deleteAll();
        //then
        Assertions.assertThat(repository.findByTransactionId(transaction1.getTransactionId()).isEmpty()).isEqualTo(true);

    }

    @Test
    void updateTransaction() {
        //given
        PurchaseTransaction transaction1 = new PurchaseTransaction(1L, "jaejun", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        PurchaseTransaction transaction2 = new PurchaseTransaction(1L, "jae", 1L, 1000000L, Payment.KAKAO, "2023-08-11");
        //when
        repository.createTransaction(transaction1);
        repository.createTransaction(transaction2);
        transaction1.setUserId(transaction2.getUserId());
        repository.updateTransaction(transaction1);
        //then
        Assertions.assertThat(repository.findByTransactionId(transaction1.getTransactionId()).get().getUserId()).isEqualTo(transaction2.getUserId());

    }
}