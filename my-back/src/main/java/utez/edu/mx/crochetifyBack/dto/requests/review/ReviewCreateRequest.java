package utez.edu.mx.crochetifyBack.dto.requests.review;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReviewCreateRequest {
    private String comment;
    private int score;
    private Long productId;

}
