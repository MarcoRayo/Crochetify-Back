package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.product.ProductCreateRequest;
import utez.edu.mx.crochetifyBack.services.product.ProductService;

@RestController
@RequestMapping("/products")
public class ProductContoller {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseObject> createProduct(@RequestBody ProductCreateRequest request){
        ResponseObject response = productService. createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductCreateRequest request) {
        ResponseObject response = productService.updateProduct(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getPoductById(@PathVariable Long id) {
        ResponseObject response = productService.getPoductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList> getProducts() {
        ResponseList response = productService.getProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
