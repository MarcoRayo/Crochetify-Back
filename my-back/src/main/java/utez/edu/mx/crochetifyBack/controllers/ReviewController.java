package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.review.ReviewCreateRequest;
import utez.edu.mx.crochetifyBack.services.review.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ResponseObject> createReview(@RequestBody ReviewCreateRequest request){
        ResponseObject response = reviewService.createReview(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ResponseObject> getReviewsByProductId(@PathVariable Long idProduct) {
        ResponseObject response = reviewService.getReviewsByProductId(idProduct);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
