package utez.edu.mx.crochetifyBack.dto.requests.category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryUpdateStatusRequest {
    private Long idCategory;
    private boolean status;
}
