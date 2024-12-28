package utez.edu.mx.crochetifyBack.dto.requests.direction;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectionUserRequest {
    private Long idDirection;
    private String direction;
    private String phone;
}
