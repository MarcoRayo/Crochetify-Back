package utez.edu.mx.crochetifyBack.dto.requests.direction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DirectionUpdateRequest {
    private Long idDirection;
    private String direction;
    private String phone;
}
