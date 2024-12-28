package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserUpdateStatusRequest;
import utez.edu.mx.crochetifyBack.services.user.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseObject> createUser(@RequestBody UserCreateRequest request) {
        ResponseObject response = userService.  createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        request.setIdUser(id);
        ResponseObject response = userService.updateUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseObject> updateUserStatus(@PathVariable Long id,
            @RequestBody UserUpdateStatusRequest request) {
        request.setIdUser(id);
        ResponseObject response = userService.updateUserStatus(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long id) {
        ResponseObject response = userService.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList> getAllUsers() {
        ResponseList response = userService.getUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUserbyID(@PathVariable Long id) {
        ResponseObject response = userService.deleteUserbyID(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
