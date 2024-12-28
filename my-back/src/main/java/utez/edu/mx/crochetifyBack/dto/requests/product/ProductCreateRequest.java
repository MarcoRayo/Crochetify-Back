package utez.edu.mx.crochetifyBack.dto.requests.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductCreateRequest {
    private String name;
    private String description;
    private List<Long> categoryIds;
}
