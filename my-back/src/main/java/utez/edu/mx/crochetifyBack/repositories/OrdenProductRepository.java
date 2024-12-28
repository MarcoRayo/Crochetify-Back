package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utez.edu.mx.crochetifyBack.entities.OrdenProduct;
import utez.edu.mx.crochetifyBack.entities.OrdenProductId;

@Repository
public interface OrdenProductRepository extends JpaRepository<OrdenProduct,OrdenProductId> {

}
