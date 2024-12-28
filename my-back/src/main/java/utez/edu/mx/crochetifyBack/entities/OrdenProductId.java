package utez.edu.mx.crochetifyBack.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrdenProductId implements Serializable{

    @Column(name = "id_orden")
    private Long ordenId;
    
    @Column(name = "id_stock")
    private Long stockId;
}
