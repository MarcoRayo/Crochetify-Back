package utez.edu.mx.crochetifyBack.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.crochetifyBack.entities.Image;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDTO {
    private Long stockId;
    private String color;
    private Long quantity; 
    private ProductDTO product;
    private List<Image> images;
}
