package utez.edu.mx.crochetifyBack.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "image")
public class Image {

    @Id
    @Column(name = "id_image")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @Column(name = "image",columnDefinition = "LONGTEXT")
    private String image;

    @ManyToOne()
    @JoinColumn(name = "stock_id")
    @JsonIgnore
    private Stock stock;
}
