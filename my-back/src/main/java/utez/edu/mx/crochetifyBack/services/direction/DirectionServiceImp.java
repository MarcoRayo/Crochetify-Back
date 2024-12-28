package utez.edu.mx.crochetifyBack.services.direction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.direction.SetDefaultDirectionRequest;
import utez.edu.mx.crochetifyBack.entities.Direction;
import utez.edu.mx.crochetifyBack.entities.User;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.repositories.DirectionRepository;
import utez.edu.mx.crochetifyBack.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirectionServiceImp implements DirectionService{
    private static final Logger log = LoggerFactory.getLogger(DirectionServiceImp.class);


    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseObject createDirection(DirectionCreateRequest request) {
        try {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new CustomException("Usuario no encontrado"));


            Direction direction = Direction.builder()
                    .direction(request.getDirection())
                    .phone(request.getPhone())
                    .user(user)
                    .build();


            directionRepository.save(direction);

            return new ResponseObject(true, "Dirección registrada con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al registrar la dirección: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al registrar la dirección");
        }
    }

    @Override
    public ResponseObject updateDirection(Long id, DirectionUpdateRequest request) {
        try {
            if (id == null) {
                throw new CustomException("El ID de la dirección no puede ser nulo");
            }

            Direction existingDirection = directionRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Dirección no encontrada"));

            existingDirection.setDirection(request.getDirection());
            existingDirection.setPhone(request.getPhone());

            directionRepository.save(existingDirection);

            return new ResponseObject(true, "Dirección actualizada con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al actualizar la dirección: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al actualizar la dirección");
        }
    }

    @Override
    public ResponseObject getDirectionsByUserId(Long idUser) {
        try {
            if (idUser == null) {
                throw new CustomException("El ID del usuario no puede ser nulo");
            }

            List<Direction> directions = directionRepository.findByUser_IdUser(idUser);

            if (directions.isEmpty()) {
                return new ResponseObject(true, "No se encontraron direcciones asociadas al usuario", Collections.emptyMap());
            }

            Map<String, Object> directionMap = directions.stream()
                    .collect(Collectors.toMap(
                            direction -> String.valueOf(direction.getIdDirection()),
                            direction -> Map.of(
                                    "idDirection", direction.getIdDirection(),
                                    "direction", direction.getDirection(),
                                    "phone", direction.getPhone(),
                                    "isDefault",direction.isDefault()
                            )
                    ));

            return new ResponseObject(true, "Direcciones encontradas con éxito", directionMap);

        } catch (Exception e) {
            log.error("Ocurrió un error al buscar las direcciones: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al buscar las direcciones");
        }
    }

    @Override
    public ResponseObject setDefaultDirection(SetDefaultDirectionRequest request) {
        try {
            if (request.getDirectionId() == null || request.getUserId() == null) {
                throw new CustomException("El ID de la dirección o del usuario no puede ser nulo");
            }

            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new CustomException("Usuario no encontrado"));

            List<Direction> directions = directionRepository.findByUser(user);

            if (directions.isEmpty()) {
                return new ResponseObject(true, "El usuario no tiene direcciones registradas", null);
            }

            directions.forEach(direction -> {
                if (direction.isDefault()) {
                    direction.setDefault(false);
                    directionRepository.save(direction);
                }
            });

            Direction direction = directionRepository.findById(request.getDirectionId())
                    .orElseThrow(() -> new CustomException("Dirección no encontrada"));

            if (!direction.getUser().equals(user)) {
                throw new CustomException("La dirección no pertenece al usuario actual");
            }

            direction.setDefault(true);
            directionRepository.save(direction);

            return new ResponseObject(true, "Dirección marcada como predeterminada exitosamente", null);

        } catch (CustomException e) {
            log.error("Error de validación: {}", e.getMessage());
            return new ResponseObject(false, e.getMessage(), null);
        } catch (Exception e) {
            log.error("Ocurrió un error inesperado: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error inesperado al establecer la dirección predeterminada");
        }
    }



}
