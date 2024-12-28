package utez.edu.mx.crochetifyBack.services.direction;

import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.direction.SetDefaultDirectionRequest;

public interface DirectionService {
    ResponseObject createDirection(DirectionCreateRequest request);
    ResponseObject updateDirection(Long id,DirectionUpdateRequest request);
    ResponseObject getDirectionsByUserId(Long userId);
    ResponseObject setDefaultDirection(SetDefaultDirectionRequest request);

}
