package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.shipment.ShipmentCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.shipment.ShipmentUpdateRequest;
import utez.edu.mx.crochetifyBack.services.shipment.ShipmentService;

@RestController
@RequestMapping("/shipment")
public class ShipmnetController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<ResponseObject> createShipment(@RequestBody ShipmentCreateRequest request){
        ResponseObject response = shipmentService.createShipment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getShipmentById(@PathVariable Long id) {
        ResponseObject response = shipmentService.getShipmentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList> getStocks() {
        ResponseList response = shipmentService.getShipments();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateShipment(
            @PathVariable Long id,
            @RequestBody ShipmentUpdateRequest request) {
        ResponseObject response = shipmentService.updateShipment(id,request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ResponseObject> updateShipmentStatus(
            @PathVariable Long id) {
        ResponseObject response = shipmentService.updateStatusShipment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
