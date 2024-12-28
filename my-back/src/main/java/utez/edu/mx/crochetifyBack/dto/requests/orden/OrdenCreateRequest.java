package utez.edu.mx.crochetifyBack.dto.requests.orden;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrdenCreateRequest {
    private Long idUser;
    private Long idDirection;
}
