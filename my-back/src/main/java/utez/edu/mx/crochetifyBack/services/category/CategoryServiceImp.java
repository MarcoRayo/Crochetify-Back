package utez.edu.mx.crochetifyBack.services.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryUpdateStatusRequest;
import utez.edu.mx.crochetifyBack.entities.Category;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.exceptions.CustomNotFoundException;
import utez.edu.mx.crochetifyBack.repositories.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImp implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImp.class);

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ResponseObject createCategory(CategoryCreateRequest request) {
        try {
            Category category = Category.builder()
                    .name(request.getName())
                    .status(true)
                    .build();
            categoryRepository.save(category);
            return new ResponseObject(true, "Categoria registrada exitosamente ", null);
        } catch (Exception e) {
            log.error("Ocurrio un error al registar la categoria : {}", e.getMessage(), e);
            throw new CustomException("Ocurrio un error al registrar la categoria");
        }
    }

    @Override
    public ResponseObject updateCategory(CategoryUpdateRequest request) {
        try {
            Category currentCategory = categoryRepository.findById(request.getIdCategory())
                    .orElseThrow(() -> new CustomNotFoundException(
                            "Categoría no encontrada"));

            if (!currentCategory.getName().equals(request.getName())) {
                currentCategory.setName(request.getName());
                categoryRepository.save(currentCategory);
            }

            return new ResponseObject(true, "Nombre de la categoría actualizado con éxito", null);
        } catch (CustomNotFoundException e) {
            log.warn("Intento de actualizar una categoría que no existe: {}");
            throw e;
        } catch (Exception e) {
            log.error("Ocurrió un error al actualizar el nombre de la categoría: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al actualizar el nombre de la categoría");
        }
    }

    @Override
    public ResponseObject updateCategoryStatus(CategoryUpdateStatusRequest request) {
        try {
            Category currentCategory = categoryRepository.findById(request.getIdCategory())
                    .orElseThrow(() -> new CustomNotFoundException(
                            "Categoria con ID" + request.getIdCategory() + " no encontrado"));

            if (currentCategory.isStatus() != request.isStatus()) {
                currentCategory.setStatus(request.isStatus());
            }
            categoryRepository.save(currentCategory);
            return new ResponseObject(true, "Estado de la Categoria actualizado con exito", null);
        } catch (CustomNotFoundException e) {
            log.warn("Intento de Actualizar una categoria que no existe: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Ocurrio un error al actualizar el estado de la categoria: {}", e.getMessage());
            throw new CustomException("Ocurrio un eror al actualizar el estado de la categoria");
        }
    }

    @Override
    public ResponseObject getCategoryById(Long idCategory) {
        try {
            Category currentCategory = categoryRepository.findById(idCategory)
                    .orElseThrow(() -> new CustomNotFoundException("Categoria con ID " + idCategory + " no encontrado"));

            return createResponseObject("Categoria recuperada con éxito", currentCategory);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de recuperar una categoria que no existe: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar la categoria: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar la categoria");
        }
    }

    @Override
    public ResponseList getCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            if (categories.isEmpty()) {
                throw new CustomNotFoundException("No existen categorias registradas");
            }
            return createResponseList("Categorias recuperadas con éxito", categories);
        } catch (CustomNotFoundException e) {
            log.warn("Error: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar las categorias: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar las categorias");
        }
    }
    private ResponseObject createResponseObject(String message, Category category) {
        Map<String, Object> response = new HashMap<>();
        response.put("category", category);
        return new ResponseObject(true, message, response);
    }

    private ResponseList createResponseList(String message, List<Category> categories) {
        Map<String, List<?>> response = new HashMap<>();
        response.put("categories", categories);
        return new ResponseList(true, message, response);
    }
}
