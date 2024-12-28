package utez.edu.mx.crochetifyBack.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table( name = "orden_direction")
public class OrdenDirection {
    @Id
    @Column(name = "id_oden_direction")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdenDirection;

    @Column(name = "direction")
    private String direction;

    @Column(name = "phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "id_orden", nullable = false)
    @JsonIgnore
    private Orden orden;

}
