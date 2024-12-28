package utez.edu.mx.crochetifyBack.dto.requests.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateRequest {

    private String name;
    private String email;
    private String password;
    private String image;

}
