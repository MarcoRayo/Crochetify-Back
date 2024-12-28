package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utez.edu.mx.crochetifyBack.entities.Direction;
import utez.edu.mx.crochetifyBack.entities.User;

import java.util.List;
@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {


    List<Direction> findByUser_IdUser(Long idUser);
    List<Direction> findByUser(User user);
}
