package utez.edu.mx.crochetifyBack.dto.requests.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateStatusRequest {
    private Long idUser;
    private boolean status;
}
