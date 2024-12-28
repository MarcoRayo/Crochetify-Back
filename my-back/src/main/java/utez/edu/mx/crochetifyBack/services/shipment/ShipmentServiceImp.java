package utez.edu.mx.crochetifyBack.services.shipment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.ShipmentDTO;
import utez.edu.mx.crochetifyBack.dto.requests.shipment.ShipmentCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.shipment.ShipmentUpdateRequest;
import utez.edu.mx.crochetifyBack.entities.Orden;
import utez.edu.mx.crochetifyBack.entities.Shipment;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.exceptions.CustomNotFoundException;
import utez.edu.mx.crochetifyBack.repositories.OrdenRepository;
import utez.edu.mx.crochetifyBack.repositories.ShipmentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ShipmentServiceImp implements ShipmentService{

    private static final Logger log = LoggerFactory.getLogger(ShipmentServiceImp.class);

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public ResponseObject createShipment(ShipmentCreateRequest request) {
        try {
            Orden orden = ordenRepository.findById(request.getIdOrden())
                    .orElseThrow(() -> new CustomException("Orden no encontrada con el ID: " + request.getIdOrden()));

            Shipment shipment = Shipment.builder()
                    .status(1)
                    .shipping_day(request.getShipping_day())
                    .orden(orden)
                    .build();

            shipmentRepository.save(shipment);

            return new ResponseObject(true, "Shipment registrado con éxito", null);

        } catch (CustomException e) {
            log.error("Error al registrar Shipment: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Ocurrió un error al registrar el Shipment: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al registrar el Shipment");
        }
    }


    @Override
    public ResponseList getShipments() {
        try {
            List<Shipment> shipments = shipmentRepository.findAll();
            if (shipments.isEmpty()) {
                throw new CustomNotFoundException("No existen shipments registrados");
            }

            List<ShipmentDTO> shipmentDTOs = shipments.stream()
                    .map(this::convertToShipmentDTO)
                    .collect(Collectors.toList());

            return createResponseList("Shipments recuperados con éxito", shipmentDTOs);
        } catch (CustomNotFoundException e) {
            log.warn("Error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar los shipments: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar los shipments");
        }
    }

    @Override
    public ResponseObject updateShipment(Long id, ShipmentUpdateRequest request) {
        try {
            Shipment existingShipment = shipmentRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Shipment no encontrado con el ID: " + id));

            existingShipment.setDelivery_day(request.getDelivery_day());
            existingShipment.setStatus(2);

            shipmentRepository.save(existingShipment);

            return new ResponseObject(true, "Shipment actualizado con éxito", null);

        } catch (CustomException e) {
            log.error("Error al actualizar Shipment: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Ocurrió un error al actualizar el Shipment: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al actualizar el Shipment");
        }
    }

    @Override
    public ResponseObject updateStatusShipment(Long id) {
        try {
            Shipment existingShipment = shipmentRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Shipment no encontrado con el ID: " + id));

            if (existingShipment.getStatus() != 2) {
                throw new CustomException("El estado del Shipment debe ser 2 para actualizarlo a 3.");
            }

            existingShipment.setStatus(3);

            shipmentRepository.save(existingShipment);

            return new ResponseObject(true, "Estado del Shipment actualizado a 3 con éxito", null);

        } catch (CustomException e) {
            log.error("Error al actualizar el estado del Shipment: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            // Manejo de otros errores
            log.error("Ocurrió un error al actualizar el estado del Shipment: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al actualizar el estado del Shipment");
        }
    }



    @Override
    public ResponseObject getShipmentById(Long idShipment) {
        try {
            Shipment currentShipment = shipmentRepository.findById(idShipment)
                    .orElseThrow(() -> new CustomNotFoundException("Shipment con ID " + idShipment + " no encontrado"));
            ShipmentDTO shipmentDTO=   convertToShipmentDTO(currentShipment);
            return createResponseObject("Shipment recuperado con éxito", shipmentDTO);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de recuperar el shipment que no existe: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar el shipment: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar el shipment");
        }
    }

    private ResponseObject createResponseObject(String message, ShipmentDTO shipment) {
        Map<String, Object> response = new HashMap<>();
        response.put("shipment", shipment);
        return new ResponseObject(true, message, response);
    }

    private ResponseList createResponseList(String message, List<ShipmentDTO> shipments) {
        Map<String, List<?>> response = new HashMap<>();
        response.put("shipments", shipments);
        return new ResponseList(true, message, response);
    }

    private ShipmentDTO convertToShipmentDTO(Shipment shipment) {
        return ShipmentDTO.builder()
                .idOrden(shipment.getOrden().getIdOrden())
                .total(shipment.getOrden().getTotal())
                .status(shipment.getStatus())
                .shipping_day(shipment.getShipping_day() != null ? shipment.getShipping_day().toString() : "")
                .delivery_day(shipment.getDelivery_day() != null ? shipment.getDelivery_day().toString() : "")
                .build();
    }
    

}
