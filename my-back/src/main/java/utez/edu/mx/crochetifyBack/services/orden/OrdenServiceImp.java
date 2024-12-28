package utez.edu.mx.crochetifyBack.services.orden;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utez.edu.mx.crochetifyBack.dto.OrdenResponse;
import utez.edu.mx.crochetifyBack.dto.ProductDTO;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.StockDTO;
import utez.edu.mx.crochetifyBack.dto.requests.orden.OrdenCreateRequest;
import utez.edu.mx.crochetifyBack.entities.Cart;
import utez.edu.mx.crochetifyBack.entities.Direction;
import utez.edu.mx.crochetifyBack.entities.Orden;
import utez.edu.mx.crochetifyBack.entities.OrdenDirection;
import utez.edu.mx.crochetifyBack.entities.OrdenProduct;
import utez.edu.mx.crochetifyBack.entities.OrdenProductId;
import utez.edu.mx.crochetifyBack.entities.Stock;
import utez.edu.mx.crochetifyBack.entities.User;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.exceptions.CustomNotFoundException;
import utez.edu.mx.crochetifyBack.repositories.CartRepository;
import utez.edu.mx.crochetifyBack.repositories.DirectionRepository;
import utez.edu.mx.crochetifyBack.repositories.OrdenDirectionRepository;
import utez.edu.mx.crochetifyBack.repositories.OrdenProductRepository;
import utez.edu.mx.crochetifyBack.repositories.OrdenRepository;
import utez.edu.mx.crochetifyBack.repositories.StockRepository;
import utez.edu.mx.crochetifyBack.repositories.UserRepository;

@Service
public class OrdenServiceImp implements OrdenService {

        private static final Logger log = LoggerFactory.getLogger(OrdenServiceImp.class);

        @Autowired
        private OrdenRepository ordenRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private CartRepository cartRepository;

        @Autowired
        private OrdenProductRepository ordenProductRepository;

        @Autowired
        private OrdenDirectionRepository ordenDirectionRepository;

        @Autowired
        private DirectionRepository directionRepository;

        @Autowired
        private StockRepository stockRepository;

        @Override
        @Transactional
        public ResponseObject createOrden(OrdenCreateRequest request) {
                User currentUser = userRepository.findById(request.getIdUser())
                                .orElseThrow(() -> new CustomNotFoundException("Usuario no encontrado"));

                Cart currentCart = cartRepository.findByUser(currentUser)
                                .orElseThrow(() -> new CustomNotFoundException(
                                                "El usuario no cuenta con un carrito registrado"));
                Direction currentDirection = directionRepository.findById(request.getIdDirection())
                                .orElseThrow(() -> new CustomNotFoundException("Direccion no econtrada"));
                try {

                        Orden orden = Orden.builder()
                                        .purchase_day(LocalDate.now())
                                        .status(true)
                                        .total(currentCart.getTotal())
                                        .user(currentUser)
                                        .build();

                        Orden savedOrden = ordenRepository.save(orden);

                        List<OrdenProduct> ordenProducts = currentCart.getCartProducts().stream()
                                        .map(cartProduct -> {
                                                OrdenProductId ordenProductId = OrdenProductId.builder()
                                                                .ordenId(savedOrden.getIdOrden())
                                                                .stockId(cartProduct.getStock().getIdStock())
                                                                .build();

                                                return OrdenProduct.builder()
                                                                .id(ordenProductId)
                                                                .orden(orden)
                                                                .stock(cartProduct.getStock())
                                                                .quantity(cartProduct.getQuantity())
                                                                .build();
                                        })
                                        .collect(Collectors.toList());

                        ordenProductRepository.saveAll(ordenProducts);

                        currentCart.getCartProducts().clear();
                        cartRepository.delete(currentCart);

                        OrdenDirection ordenDirection = OrdenDirection.builder()
                        .direction(currentDirection.getDirection())
                        .phone(currentDirection.getPhone())
                        .orden(savedOrden)
                        .build();
                        ordenDirectionRepository.save(ordenDirection);
                        for (OrdenProduct ordenProduct : ordenProducts) {
                                Stock stock = ordenProduct.getStock();
                                Long newQuantity = stock.getQuantity() - ordenProduct.getQuantity();

                                if (newQuantity < 0) {
                                        throw new CustomException(
                                                        "Stock insuficiente para el producto: " + stock.getIdStock());
                                }

                                stock.setQuantity(newQuantity);
                                stockRepository.save(stock);
                        }

                        return new ResponseObject(true, "Pedido registrada con éxito", null);

                } catch (Exception e) {
                        log.error("Ocurrió un error al registrar el pedido: {}", e.getMessage(), e);
                        throw new CustomException( e.getMessage());
                }
        }

        @Override
        public ResponseObject getOrdenById(Long idOrden) {
                Orden orden = ordenRepository.findById(idOrden)
                                .orElseThrow(() -> new CustomNotFoundException("Orden no encontrada"));
                try {
                        OrdenResponse responseDTO = convertToOrdenResponseDTO(orden);
                        return createResponseObject("Pedido recuperado con exito", responseDTO);
                } catch (Exception e) {
                        throw new CustomException("Ocurrió un error al registrar el pedido");

                }

        }

        @Override
        public ResponseList getAllOrdenesByUser(Long idUser) {
                User currentUser = userRepository.findById(idUser)
                                .orElseThrow(() -> new CustomNotFoundException("Usuario no encontrado"));
                try {
                        List<Orden> ordenes = ordenRepository.findByUser(currentUser);

                        List<OrdenResponse> ordenResponses = ordenes.stream()
                                        .map(this::convertToOrdenResponseDTO)
                                        .collect(Collectors.toList());

                        return createResponseList("Pedidos por usuario recuperados con éxito", ordenResponses);
                } catch (Exception e) {
                        throw new CustomException("Ocurrió un error al registrar el pedido");

                }
        }

        @Override
        public ResponseList getAllOrdenes() {
                try {
                        List<Orden> ordenes = ordenRepository.findAll();

                        List<OrdenResponse> ordenResponses = ordenes.stream()
                                        .map(this::convertToOrdenResponseDTO)
                                        .collect(Collectors.toList());

                        return createResponseList("Pedidos recuperados con éxito", ordenResponses);

                } catch (Exception e) {
                        throw new CustomException("Ocurrió un error al registrar el pedido" + e.getMessage());
                }

        }

        private ResponseList createResponseList(String message, List<?> list) {
                if (!list.isEmpty()) {
                        Map<String, List<?>> response = new HashMap<>();
                        response.put("pedidosUsuario", list);
                        return new ResponseList(true, message, response);
                }
                return new ResponseList(true, message, null);

        }

        private ResponseObject createResponseObject(String message, OrdenResponse orden) {
                if (orden != null) {
                        Map<String, Object> response = new HashMap<>();
                        response.put("pedido", orden);
                        return new ResponseObject(true, message, response);

                }
                return new ResponseObject(true, message, null);
        }

        public OrdenResponse convertToOrdenResponseDTO(Orden orden) {
                List<StockDTO> stockDTOs = orden.getOrdenProducts().stream()
                                .map(cartProduct -> StockDTO.builder()
                                                .stockId(cartProduct.getStock().getIdStock())
                                                .color(cartProduct.getStock().getColor())
                                                .quantity(cartProduct.getQuantity())
                                                .images(cartProduct.getStock().getImages())
                                                .product(ProductDTO.builder()
                                                                .idProduct(cartProduct.getStock().getProduct()
                                                                                .getIdProduct())
                                                                .name(cartProduct.getStock().getProduct().getName())
                                                                .description(cartProduct.getStock().getProduct()
                                                                                .getDescription())
                                                                .status(cartProduct.getStock().getProduct().isStatus())
                                                                .build())
                                                .build())
                                .collect(Collectors.toList());

                return OrdenResponse.builder()
                                .idOrden(orden.getIdOrden())
                                .total(orden.getTotal())
                                .idShipment(orden.getShipment() != null && orden.getShipment().getIdShipment() != null
                                                ? orden.getShipment().getIdShipment()
                                                : 0)
                                .statusShipment(orden.getShipment() != null && orden.getShipment().getStatus() != 0
                                                ? orden.getShipment().getStatus()
                                                : 0)
                                .shipping_day(orden.getShipment() != null
                                                && orden.getShipment().getShipping_day() != null
                                                                ? orden.getShipment().getShipping_day().toString()
                                                                : "")
                                .delivery_day(orden.getShipment() != null
                                                && orden.getShipment().getDelivery_day() != null
                                                                ? orden.getShipment().getDelivery_day().toString()
                                                                : "")
                                .ordenDirection(orden.getOrdenDirection() != null
                                                && orden.getOrdenDirection().getDirection() != null
                                                                ? orden.getOrdenDirection().getDirection()
                                                                : "")
                                .phoneDirection(orden.getOrdenDirection() != null
                                                && orden.getOrdenDirection().getPhone() != null
                                                                ? orden.getOrdenDirection().getPhone()
                                                                : "")
                                .ordenProducts(stockDTOs)
                                .build();
        }

}
