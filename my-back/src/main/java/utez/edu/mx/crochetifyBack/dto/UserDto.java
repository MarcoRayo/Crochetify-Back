package utez.edu.mx.crochetifyBack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long idUser;
    private String name;
    private String email;
    private boolean status;
    private String image;

}
