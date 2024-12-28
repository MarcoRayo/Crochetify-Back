package utez.edu.mx.crochetifyBack.services.orden;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.orden.OrdenCreateRequest;

public interface OrdenService {
    ResponseObject createOrden(OrdenCreateRequest request);

    ResponseObject getOrdenById(Long idOrden);

    ResponseList getAllOrdenesByUser(Long idUser);

    ResponseList getAllOrdenes();

}
