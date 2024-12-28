package utez.edu.mx.crochetifyBack.services.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.stock.StockCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.stock.StockUpdateRequest;
import utez.edu.mx.crochetifyBack.entities.Image;
import utez.edu.mx.crochetifyBack.entities.Product;
import utez.edu.mx.crochetifyBack.entities.Stock;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.exceptions.CustomNotFoundException;
import utez.edu.mx.crochetifyBack.repositories.ImageRepository;
import utez.edu.mx.crochetifyBack.repositories.ProductRepository;
import utez.edu.mx.crochetifyBack.repositories.StockRepository;
import java.util.*;

@Service
public class StockServiceImp implements StockService{
    private static final Logger log = LoggerFactory.getLogger(StockServiceImp.class);

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ImageRepository imageRepository;


    @Override
    public ResponseObject createStock(StockCreateRequest request) {
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new CustomException("Producto no encontrado"));

            Stock stock = Stock.builder()
                    .color(request.getColor())
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .status(true)
                    .product(product)
                    .build();

            Stock savedStock = stockRepository.save(stock);

            if (request.getImages() != null && !request.getImages().isEmpty()) {
                request.getImages().forEach(base64Image -> {
                    Image image = Image.builder()
                            .image(base64Image)
                            .stock(savedStock)
                            .build();
                    imageRepository.save(image);
                });
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> stockMap = mapper.convertValue(savedStock, Map.class);

            return new ResponseObject(true, "Stock registrado con éxito", stockMap);

        } catch (Exception e) {
            log.error("Ocurrió un error al registrar el stock: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al registrar el stock");
        }
    }



    @Override
    public ResponseObject getStockById(Long idStock) {
        try {
            Stock currentStock = stockRepository.findById(idStock)
                    .orElseThrow(() -> new CustomNotFoundException("Product con ID " + idStock + " no encontrado"));

            return createResponseObject("Stock recuperado con éxito", currentStock);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de recuperar el stock que no existe: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar el stock: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar el stock");
        }
    }

    @Override
    public ResponseList getStocks() {
        try {
            List<Stock> stocks = stockRepository.findAll();
            if (stocks.isEmpty()) {
                throw new CustomNotFoundException("No existen stocks registrados");
            }
            return createResponseList("Stocks recuperados con éxito", stocks);
        } catch (CustomNotFoundException e) {
            log.warn("Error: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar los stocks: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar los stocks");
        }
    }




    @Override
    @Transactional
    public ResponseObject updateStock(Long id,StockUpdateRequest request) {
        try {
            Stock stock = stockRepository.findById(request.getIdStock())
                    .orElseThrow(() -> new CustomException("Stock no encontrado"));

            if (request.getColor() != null) {
                stock.setColor(request.getColor());
            }
            if (request.getPrice() != null) {
                stock.setPrice(request.getPrice());
            }
            if (request.getQuantity() != null) {
                stock.setQuantity(request.getQuantity());
            }
            if (request.getStatus() != null) {
                stock.setStatus(request.getStatus());
            }

            Stock updatedStock = stockRepository.save(stock);

            if (request.getImages() != null && !request.getImages().isEmpty()) {
                imageRepository.deleteByStock(updatedStock);

                request.getImages().forEach(base64Image -> {
                    Image image = Image.builder()
                            .image(base64Image)
                            .stock(updatedStock)
                            .build();
                    imageRepository.save(image);
                });
            }
            return new ResponseObject(true, "Stock actualizado con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al actualizar el stock: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al actualizar el stock");
        }
    }



    private ResponseObject createResponseObject(String message, Stock stock) {
        Map<String, Object> response = new HashMap<>();
        response.put("stock", stock);
        return new ResponseObject(true, message, response);
    }

    private ResponseList createResponseList(String message, List<Stock> stocks) {
        Map<String, List<?>> response = new HashMap<>();
        response.put("stocks", stocks);
        return new ResponseList(true, message, response);
    }

}
