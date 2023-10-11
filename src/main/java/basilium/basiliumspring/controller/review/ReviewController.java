package basilium.basiliumspring.controller.review;
import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.domain.review.Review;
import basilium.basiliumspring.service.review.ReviewService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model; // 스프링의 Model을 가져옴
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
/*
/reviews/add: 리뷰를 추가하는 페이지에서 평점과 함께 리뷰를 작성하고 제출할 때 호출됩니다.
/reviews/update: 리뷰를 수정하는 페이지에서 리뷰를 수정하고 제출할 때 호출됩니다.
/reviews/delete: 특정 리뷰를 삭제할 때 호출됩니다.
/reviews/get/{reviewId}: 특정 리뷰의 상세 정보를 표시합니다.
/reviews/getAll: 모든 리뷰 목록을 표시합니다.
/reviews/calculateAverageRating/{productId}: 특정 제품의 평균 평점을 JSON 형식으로 반환합니다. 이 정보를 웹 페이지에 표시하거나 다른 용도로 사용할 수 있습니다.
 */
@Slf4j
@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ObjectMapper objectMapper;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService, ObjectMapper objectMapper) {
        this.reviewService = reviewService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute("review") Review review) {
        reviewService.addReview(review);
        return "redirect:/product/details/" + review.getProductId(); // 리뷰를 추가하고 해당 제품 페이지로 리다이렉트
    }

    @PostMapping("/update")
    public String updateReview(@ModelAttribute("review") Review review) {
        reviewService.updateReview(review);
        return "redirect:/product/details/" + review.getProductId(); // 리뷰를 업데이트하고 해당 제품 페이지로 리다이렉트
    }

    @PostMapping("/delete")
    public String deleteReview(@RequestParam("reviewId") Long reviewId, @RequestParam("productId") Long productId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/product/details/" + productId; // 리뷰를 삭제하고 해당 제품 페이지로 리다이렉트
    }

    @GetMapping("/get/{reviewId}")
    public String getReviewById(@PathVariable("reviewId") Long reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        return "review-details"; // 특정 리뷰의 상세 정보 페이지로 이동
    }

    @GetMapping("/getAll")
    public String getAllReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "all-reviews"; // 모든 리뷰 목록을 표시하는 페이지로 이동
    }

    // 리뷰 평점
    @GetMapping("/calculateAverageRating/{productId}")
    @ResponseBody
    public double calculateAverageRating(@PathVariable("productId") Long productId) {
        return reviewService.calculateAverageRating(productId);
    }

    @PostMapping("/reviewInfos")
    public ResponseEntity<String> uploadProduct(@RequestParam("review") String strReview, @RequestPart("files") MultipartFile[] files) {
        try {
            log.info(strReview);
            // JSON 문자열을 Product 객체로 변환
            JsonNode rootNode = objectMapper.readTree(strReview);
            Review review = new Review();
            review.setReview(rootNode.get("review").asText());
            review.setProductId(rootNode.get("productId").asLong());
            review.setRating(rootNode.get("rating").asInt());
            review.setUserId(rootNode.get("userId").asText());
            reviewService.addReview(review);
            log.info("여기서 까지 OK");
            ResponseEntity<String> ret = reviewService.savePhotoFiles(files, review.getReviewId());

            if (ret.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return ret;
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(review.getReviewId().toString());
        } catch (Exception e) {
            // JSON 파싱 오류 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
    }


    // 기타 리뷰 관련 기능 추가 가능
}

