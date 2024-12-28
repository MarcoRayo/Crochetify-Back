package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utez.edu.mx.crochetifyBack.entities.Image;
import utez.edu.mx.crochetifyBack.entities.Stock;
@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    void deleteByStock(Stock updatedStock);
}
