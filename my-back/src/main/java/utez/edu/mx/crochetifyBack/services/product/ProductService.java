package utez.edu.mx.crochetifyBack.services.product;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.product.ProductCreateRequest;

public interface ProductService {

    ResponseObject createProduct(ProductCreateRequest request);
    ResponseObject updateProduct(Long id, ProductCreateRequest request);

    ResponseObject getPoductById(Long idProduct);

    ResponseList getProducts();
}
