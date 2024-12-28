package utez.edu.mx.crochetifyBack.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.orden.OrdenCreateRequest;
import utez.edu.mx.crochetifyBack.services.orden.OrdenService;

@RestController
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping
    public ResponseEntity<ResponseObject> createOrden(@RequestBody OrdenCreateRequest request){
        ResponseObject response = ordenService.createOrden(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getOrdenById(@PathVariable Long id) {
        ResponseObject response = ordenService.getOrdenById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseList> getAllOrdenesByUser(@PathVariable Long id) {
        ResponseList response = ordenService.getAllOrdenesByUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList> getAllOrdenes() {
        ResponseList response = ordenService.getAllOrdenes();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
