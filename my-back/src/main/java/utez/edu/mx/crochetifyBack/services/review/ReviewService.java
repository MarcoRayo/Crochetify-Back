package utez.edu.mx.crochetifyBack.services.review;

import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.review.ReviewCreateRequest;

public interface ReviewService {
    ResponseObject createReview(ReviewCreateRequest request);
    ResponseObject getReviewsByProductId(Long productId);
}
