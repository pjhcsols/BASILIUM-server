package basilium.basiliumspring.controller.like;

import basilium.basiliumspring.domain.like.LikeDTO;
import basilium.basiliumspring.service.like.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/like")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/doLike")
    public ResponseEntity<String> doLike(@RequestBody LikeDTO likeDTO){
        System.out.println(likeDTO.getProductId().toString() + " " + likeDTO.getUserId());

        likeService.likeProduct(likeDTO);
        return ResponseEntity.ok("successfully like product");
    }

    @PostMapping("/unLike")
    public ResponseEntity<String> unLike(@RequestBody LikeDTO likeDTO) {
        Optional<LikeDTO> ret = likeService.unLikeProduct(likeDTO);

        if (ret.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok("Successfully unliked the product");
        }
    }

    @GetMapping("/likeInfos")
    public ResponseEntity<List<LikeDTO>> getLikeInfoByUserId(@RequestBody String userId){
        System.out.println(userId);
        List<LikeDTO> likeDTOList = likeService.getUserLikeInfo(userId);
        return ResponseEntity.ok(likeDTOList);
    }
}
