package utez.edu.mx.crochetifyBack.dto.requests.category;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryUpdateRequest {
    private Long idCategory;

    private String name;
}
