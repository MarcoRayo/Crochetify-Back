package utez.edu.mx.crochetifyBack.dto.requests.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class StockUpdateRequest {
    private Long idStock;

    private String color;

    private Double price;

    private Long quantity;

    private Boolean status;

    private List<String> images;
}
