package basilium.basiliumspring.controller.purchaseTransaction;

import basilium.basiliumspring.domain.like.LikeDTO;
import basilium.basiliumspring.domain.purchasetransaction.PurchaseTransaction;
import basilium.basiliumspring.service.like.LikeService;
import basilium.basiliumspring.service.purchasetransaction.PurchaseTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchaseTransaction")
public class PurchaseTransactionController {
    private PurchaseTransactionService purchaseTransactionService;

    @Autowired
    public PurchaseTransactionController(PurchaseTransactionService purchaseTransactionService) {
        this.purchaseTransactionService = purchaseTransactionService;
    }

    @PostMapping("/saveTransaction")
    public ResponseEntity<String> saveTransaction(@RequestBody PurchaseTransaction purchaseTransaction){

        purchaseTransactionService.savePurchaseTransaction(purchaseTransaction);
        return ResponseEntity.ok("successfully save transaction");
    }

    @PostMapping("/deleteTransaction")
    public ResponseEntity<String> deleteTransaction(@RequestParam String userId, @RequestParam Long productId) {
        purchaseTransactionService.deletePurchaseTransaction(userId, productId);
        return ResponseEntity.ok("successfully delete transaction");
    }

    @GetMapping("/transactionInfos")
    public ResponseEntity<List<PurchaseTransaction>> getTransactionInfoByUserId(@RequestParam String userId){
        List<PurchaseTransaction> ret = purchaseTransactionService.getPurchaseTransactionByUserId(userId);
        return ResponseEntity.ok(ret);
    }
}
