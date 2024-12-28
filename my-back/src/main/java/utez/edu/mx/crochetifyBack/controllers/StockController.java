package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.stock.StockCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.stock.StockUpdateRequest;
import utez.edu.mx.crochetifyBack.services.stock.StockService;

@RequestMapping("/stock")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<ResponseObject> createStock(@RequestBody StockCreateRequest request) {
        ResponseObject response = stockService.createStock(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getStockById(@PathVariable Long id) {
        ResponseObject response = stockService.getStockById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList> getStocks() {
        ResponseList response = stockService.getStocks();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateStock(
            @PathVariable Long id,
            @RequestBody StockUpdateRequest request) {
        ResponseObject response = stockService.updateStock(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
