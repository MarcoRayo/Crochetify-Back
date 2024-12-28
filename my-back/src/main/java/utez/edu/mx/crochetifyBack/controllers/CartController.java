package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.cart.CartCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.cart.CartUpdateRequest;
import utez.edu.mx.crochetifyBack.services.cart.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody CartCreateRequest request) {
        ResponseObject createdCart = cartService.createCart(request);

        return ResponseEntity.ok(createdCart);
    }

    @PutMapping
    public ResponseEntity<?> updateCart(@RequestBody CartUpdateRequest request) {
        ResponseObject createdCart = cartService.updateCart(request);
        return ResponseEntity.ok(createdCart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable Long id) {
        ResponseObject response = cartService.getCartById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
