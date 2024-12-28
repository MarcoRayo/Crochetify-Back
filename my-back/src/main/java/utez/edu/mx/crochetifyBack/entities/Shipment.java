package utez.edu.mx.crochetifyBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @Column(name = "id_shipment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShipment;


    @Column(name = "status")
    private int status;

    @Column(name = "shipping_day")
    private LocalDate shipping_day;

    @Column(name = "delivery_day")
    private LocalDate delivery_day;

    @OneToOne
    @JoinColumn(name = "id_orden", nullable = false)
    @JsonBackReference
    private Orden orden;



}
