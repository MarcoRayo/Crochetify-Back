package utez.edu.mx.crochetifyBack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDTO {
    private  Long idOrden;
    private double total;
    private int status;
    private String shipping_day;
    private String delivery_day;
}
