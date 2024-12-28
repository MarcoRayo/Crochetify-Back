package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "id_payment", nullable = false, length = 36)
    private Long idPayment;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
