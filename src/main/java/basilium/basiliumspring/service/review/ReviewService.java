package basilium.basiliumspring.service.review;

import basilium.basiliumspring.domain.product.Image;
import basilium.basiliumspring.domain.review.Review;
import basilium.basiliumspring.repository.product.ImageRepository;
import basilium.basiliumspring.repository.review.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
public class ReviewService {
    private String UPLOAD_DIR = "/Users/gimseungjun/Desktop/BASILIUM-server/reviewPhoto/";
    private final ReviewRepository reviewRepository;
    private final ImageRepository imageRepository;

    public ReviewService(ReviewRepository reviewRepository, ImageRepository imageRepository) {
        this.reviewRepository = reviewRepository;
        this.imageRepository = imageRepository;
    }
 /*
    public void addReview(Review review) {
        reviewRepository.addReview(review);
    }

 */
    public void addReview(Review review) {
        reviewRepository.addReview(review); // 리뷰 추가
    }


    public void updateReview(Review review) {
        reviewRepository.updateReview(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteReview(reviewId);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.getReviewById(reviewId);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.getReviewsByProductId(productId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.getReviewsByUserId(userId);
    }

    //리뷰 평점 계산
    public double calculateAverageRating(Long productId) {
        List<Review> reviews = reviewRepository.getReviewsByProductId(productId);
        if (reviews.isEmpty()) {
            return 0.0; // 리뷰가 없을 경우 기본값 반환
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size();
    }
    public ResponseEntity<String> savePhotoFiles(MultipartFile [] files, Long reviewId){
        List<String> uploadedFiles = new ArrayList<>();
        int cnt = 1;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    // 업로드 파일명 설정
                    String originalFileName = file.getOriginalFilename();
                    String newFileName = reviewId.toString() + "_" + Integer.toString(cnt++) + ".png";
                    File newFile = new File(UPLOAD_DIR + newFileName);

                    //Image 객체 생성
                    Image newImage = new Image();
                    newImage.setProductId(reviewId);
                    newImage.setImagePath(UPLOAD_DIR + newFileName);
                    imageRepository.addImage(newImage);

                    // 파일 저장
                    file.transferTo(newFile);

                    uploadedFiles.add(newFileName);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image upload failed");
                }
            }
        }

        if (!uploadedFiles.isEmpty()) {
            // 업로드 된 파일 목록을 로그에 기록
            for (String fileName : uploadedFiles) {
                log.info("Uploaded file: " + fileName);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Images uploaded and saved successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No images selected.");
        }
    }
}



