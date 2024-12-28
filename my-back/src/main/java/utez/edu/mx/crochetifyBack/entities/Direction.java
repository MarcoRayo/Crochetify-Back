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
@Table( name = "direction")
public class Direction {

    @Id
    @Column(name = "id_direction")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDirection;

    @Column(name = "direction", columnDefinition = "LONGTEXT")
    private String direction;

    @Column(name = "phone")
    private String phone;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_default")
    private boolean isDefault;



}
