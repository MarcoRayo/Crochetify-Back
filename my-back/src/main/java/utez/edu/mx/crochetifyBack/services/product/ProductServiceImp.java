package utez.edu.mx.crochetifyBack.services.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.product.ProductCreateRequest;
import utez.edu.mx.crochetifyBack.entities.Category;
import utez.edu.mx.crochetifyBack.entities.Product;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.exceptions.CustomNotFoundException;
import utez.edu.mx.crochetifyBack.repositories.CategoryRepository;
import utez.edu.mx.crochetifyBack.repositories.ProductRepository;

import java.util.*;

@Service
public class ProductServiceImp implements  ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImp.class);


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseObject createProduct(ProductCreateRequest request) {
        try {
            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(request.getCategoryIds()));

            Product product = Product.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .status(true)
                    .categories(categories)
                    .build();

            productRepository.save(product);

            return new ResponseObject(true, "Producto registrado con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al registrar el producto: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al registrar el producto");
        }
    }

    @Override
    public ResponseObject updateProduct(Long id, ProductCreateRequest request) {
        try {
            Product currentProduct = productRepository.findById(id)
                    .orElseThrow(() -> new CustomNotFoundException(
                            "Producto con ID " + id + " no encontrado"));

            if (!currentProduct.getName().equals(request.getName())) {
                currentProduct.setName(request.getName());
            }
            if (!currentProduct.getDescription().equals(request.getDescription())) {
                currentProduct.setDescription(request.getDescription());
            }

            if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
                Set<Category> categories = new HashSet<>(categoryRepository.findAllById(request.getCategoryIds()));
                currentProduct.setCategories(categories);
            }

            productRepository.save(currentProduct);

            return new ResponseObject(true, "Producto actualizado con éxito", null);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de actualizar un producto que no existe: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Ocurrió un error al actualizar el producto: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al actualizar el producto");
        }
    }

    @Override
    public ResponseObject getPoductById(Long idProduct) {
        try {
            Product currentProduct = productRepository.findById(idProduct)
                    .orElseThrow(() -> new CustomNotFoundException("Product con ID " + idProduct + " no encontrado"));

            return createResponseObject("Producto recuperado con éxito", currentProduct);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de recuperar un producto que no existe: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar el producto: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar el producto");
        }
    }

    @Override
    public ResponseList getProducts() {
        try {
            List<Product> products = productRepository.findAll();
            if (products.isEmpty()) {
                throw new CustomNotFoundException("No existen productos registrados");
            }
            return createResponseList("Prodcutos recuperados con éxito", products);
        } catch (CustomNotFoundException e) {
            log.warn("Error: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar los productos: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar los productos");
        }
    }



    private ResponseObject createResponseObject(String message, Product product) {
        Map<String, Object> response = new HashMap<>();
        response.put("product", product);
        return new ResponseObject(true, message, response);
    }

    private ResponseList createResponseList(String message, List<Product> products) {
        Map<String, List<?>> response = new HashMap<>();
        response.put("products", products);
        return new ResponseList(true, message, response);
    }

}
