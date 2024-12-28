package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orden_product")
public class OrdenProduct {
    @EmbeddedId
    private OrdenProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ordenId")
    @JoinColumn(name = "id_orden")
    private Orden orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("stockId")
    @JoinColumn(name = "id_stock")
    private Stock stock;

    @Column(name = "quantity", nullable = false)
    private Long quantity;
    

}
