package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utez.edu.mx.crochetifyBack.entities.Cart;
import utez.edu.mx.crochetifyBack.entities.User;

import java.util.Optional;


@Repository
public interface CartRepository  extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUser(User user);

}
