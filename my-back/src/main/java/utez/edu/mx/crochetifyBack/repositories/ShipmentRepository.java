package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utez.edu.mx.crochetifyBack.entities.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
}
