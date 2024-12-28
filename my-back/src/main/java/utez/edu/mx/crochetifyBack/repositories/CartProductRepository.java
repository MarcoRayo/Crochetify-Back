package utez.edu.mx.crochetifyBack.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utez.edu.mx.crochetifyBack.entities.CartProduct;
import utez.edu.mx.crochetifyBack.entities.CartProductId;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {
}
