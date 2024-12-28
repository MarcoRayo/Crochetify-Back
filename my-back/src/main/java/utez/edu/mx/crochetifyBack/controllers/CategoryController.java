package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryUpdateStatusRequest;
import utez.edu.mx.crochetifyBack.services.category.CategoryService;
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseObject> createCategory(@RequestBody CategoryCreateRequest request){
        ResponseObject response = categoryService. createCategory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ResponseObject> updateCategory(@RequestBody CategoryUpdateRequest request){
        ResponseObject response = categoryService.updateCategory(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseObject> updateCategoryStatus(@PathVariable long id,
        @RequestBody CategoryUpdateStatusRequest request){
        request.setIdCategory(id);
        ResponseObject response = categoryService.updateCategoryStatus(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getCategoryById(@PathVariable Long id) {
        ResponseObject response = categoryService.getCategoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseList> getAllCategories() {
        ResponseList response = categoryService.getCategories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
