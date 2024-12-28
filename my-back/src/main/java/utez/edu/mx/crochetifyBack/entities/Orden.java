package utez.edu.mx.crochetifyBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @Column(name = "id_orden")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @Column(name = "status")
    private boolean status;

    @Column(name = "total")
    private double total;

    @Column(name = "purchase_day")
    private LocalDate purchase_day;

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrdenProduct> ordenProducts = new ArrayList<>();

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private OrdenDirection ordenDirection;

}
