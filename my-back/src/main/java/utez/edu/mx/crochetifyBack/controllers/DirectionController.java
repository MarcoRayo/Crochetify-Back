package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionCreateRequest;

import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.direction.SetDefaultDirectionRequest;
import utez.edu.mx.crochetifyBack.services.direction.DirectionService;

@RestController
@RequestMapping("/directions")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    @PostMapping
    public ResponseEntity<ResponseObject> createDirection(@RequestBody DirectionCreateRequest request) {
        ResponseObject response = directionService.createDirection(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateDirection(
            @PathVariable Long id,
            @RequestBody DirectionUpdateRequest request) {
        ResponseObject response = directionService.updateDirection(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<ResponseObject> getDirectionsByUserId(@PathVariable Long idUser) {
        ResponseObject response = directionService.getDirectionsByUserId(idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/set-default")
    public ResponseEntity<ResponseObject> setDefaultDirection(@RequestBody SetDefaultDirectionRequest request) {
        ResponseObject response = directionService.setDefaultDirection(request);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

}
