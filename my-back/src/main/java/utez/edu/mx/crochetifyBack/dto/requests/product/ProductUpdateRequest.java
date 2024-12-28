package utez.edu.mx.crochetifyBack.dto.requests.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ProductUpdateRequest {
    private String name;
    private String description;
}
