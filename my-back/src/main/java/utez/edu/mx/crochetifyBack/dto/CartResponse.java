package utez.edu.mx.crochetifyBack.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse{
    private Long idCart;
    private double total;
    private List<StockDTO> cartProducts;
}
