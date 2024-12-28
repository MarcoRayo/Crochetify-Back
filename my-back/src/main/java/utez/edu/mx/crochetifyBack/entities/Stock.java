package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "id_stock")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    @Column(name = "color")
    private String color;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name= "price")
    private double price;

    @Column(name = "status")
    private boolean status;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "stock")
    private List<Image> images;


}
