package utez.edu.mx.crochetifyBack.services.shipment;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.shipment.ShipmentCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.shipment.ShipmentUpdateRequest;

public interface ShipmentService {
    ResponseObject createShipment(ShipmentCreateRequest request);
    ResponseObject getShipmentById(Long idShipment);

    ResponseList getShipments();
    ResponseObject updateShipment(Long id,ShipmentUpdateRequest request);
    ResponseObject updateStatusShipment(Long id);
}
