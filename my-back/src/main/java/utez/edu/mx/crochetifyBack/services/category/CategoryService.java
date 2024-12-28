package utez.edu.mx.crochetifyBack.services.category;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.category.CategoryUpdateStatusRequest;

public interface CategoryService {

    ResponseObject createCategory(CategoryCreateRequest request);
    ResponseObject updateCategory(CategoryUpdateRequest request);
    ResponseObject updateCategoryStatus(CategoryUpdateStatusRequest request);
    ResponseObject getCategoryById(Long idCategory);
    ResponseList getCategories();
}
