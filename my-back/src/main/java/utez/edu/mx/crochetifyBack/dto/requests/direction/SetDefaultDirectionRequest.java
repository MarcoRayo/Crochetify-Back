package utez.edu.mx.crochetifyBack.dto.requests.direction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetDefaultDirectionRequest {
    private Long directionId;
    private Long userId;
}
