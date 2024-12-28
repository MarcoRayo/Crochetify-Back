package utez.edu.mx.crochetifyBack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utez.edu.mx.crochetifyBack.entities.Orden;
import utez.edu.mx.crochetifyBack.entities.User;

@Repository
public interface OrdenRepository extends JpaRepository<Orden,Long> {
     List<Orden> findByUser(User user);

}
