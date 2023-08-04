package basilium.basiliumspring.repository.purchasetransaction;

import basilium.basiliumspring.domain.purchasetransaction.PurchaseTransaction;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryPurchaseTransactionRepository implements PurchaseTransactionRepository{
    Map<Long, PurchaseTransaction>store = new HashMap<>();
    private Long sequence = 0L;
    @Override
    public PurchaseTransaction createTransaction(PurchaseTransaction transaction) {
        transaction.setTransactionId(++sequence);
        store.put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    @Override
    public List<PurchaseTransaction> findByUserId(String userId) {
        return store.values().stream().filter(m->m.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public List<PurchaseTransaction> findByDate(String date) {
        return store.values().stream().filter(m->m.getPurchaseTime()==date).collect(Collectors.toList());
    }

    @Override
    public Optional<PurchaseTransaction> findByTransactionId(Long id) {
        return Optional.ofNullable((store.get(id)));
    }

    @Override
    public void deleteByTransactionId(Long id) {
        store.remove(id);
    }

    @Override
    public void deleteByUserId(String userId) {
        List<PurchaseTransaction> temp = store.values().stream().filter(m->m.getUserId() == userId).collect(Collectors.toList());
        for(PurchaseTransaction item : temp)
        {
            if(item.getUserId() == userId)
            {
              store.remove(item.getTransactionId());
            }
        }
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override
    public PurchaseTransaction updateTransaction(PurchaseTransaction transaction) {
        store.replace(transaction.getTransactionId(), transaction);
        return transaction;
    }
}
