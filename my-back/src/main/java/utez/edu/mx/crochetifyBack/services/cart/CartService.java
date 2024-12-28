package utez.edu.mx.crochetifyBack.services.cart;

import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.cart.CartCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.cart.CartUpdateRequest;

public interface CartService {

    ResponseObject createCart(CartCreateRequest request);

    ResponseObject getCartById(Long idCart);

    ResponseObject updateCart(CartUpdateRequest request);
}
