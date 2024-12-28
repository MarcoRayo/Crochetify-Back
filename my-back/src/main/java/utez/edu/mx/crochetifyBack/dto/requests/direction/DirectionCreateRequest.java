package utez.edu.mx.crochetifyBack.dto.requests.direction;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DirectionCreateRequest {

    private String direction;
    private String phone;
    private Long userId;

}
