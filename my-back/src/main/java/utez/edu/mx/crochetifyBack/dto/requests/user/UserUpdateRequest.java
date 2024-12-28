package utez.edu.mx.crochetifyBack.dto.requests.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateRequest {
    private Long idUser;
    private String name;
    private String image;
}
