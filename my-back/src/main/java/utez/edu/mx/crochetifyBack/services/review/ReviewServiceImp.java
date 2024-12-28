package utez.edu.mx.crochetifyBack.services.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.review.ReviewCreateRequest;
import utez.edu.mx.crochetifyBack.entities.Product;
import utez.edu.mx.crochetifyBack.entities.Review;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.repositories.ProductRepository;
import utez.edu.mx.crochetifyBack.repositories.ReviewRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImp  implements ReviewService{
    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImp.class);

        @Autowired
        private ReviewRepository reviewRepository;

        @Autowired
        private ProductRepository productRepository;


    @Override
    public ResponseObject createReview(ReviewCreateRequest request) {
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new CustomException("Producto no encontrado"));

            Review review = Review.builder()
                    .comment(request.getComment())
                    .score(request.getScore())
                    .product(product)
                    .build();

            reviewRepository.save(review);

            return new ResponseObject(true, "Review registrada con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al registrar la review: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al registrar la review");
        }
    }

    @Override
    public ResponseObject getReviewsByProductId(Long productId) {
        try {
            if (productId == null) {
                throw new CustomException("El ID del producto no puede ser nulo");
            }

            List<Review> reviews = reviewRepository.findByProduct_IdProduct(productId);

            if (reviews.isEmpty()) {
                return new ResponseObject(true, "No se encontraron reseñas asociadas al producto", Collections.emptyMap());
            }

            Map<String, Object> reviewMap = reviews.stream()
                    .collect(Collectors.toMap(
                            review -> String.valueOf(review.getIdReview()),
                            review -> Map.of(
                                    "idReview", review.getIdReview(),
                                    "comment", review.getComment(),
                                    "score", review.getScore()
                            )
                    ));

            return new ResponseObject(true, "Reseñas encontradas con éxito", reviewMap);

        } catch (Exception e) {
            log.error("Ocurrió un error al buscar las reseñas: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al buscar las reseñas");
        }
    }




}
