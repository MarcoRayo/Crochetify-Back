package utez.edu.mx.crochetifyBack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import utez.edu.mx.crochetifyBack.dto.ResponseObject;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomExceptionUnauthorized.class)
    public ResponseEntity<ResponseObject> handlerCustomUnauthorizedExeption(CustomExceptionUnauthorized exception) {
        return createErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseObject> handlerCustomeException(CustomException exception) {
        return createErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ResponseObject> handlerCustomeNotFoundException(CustomNotFoundException exception) {
        return createErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ResponseObject> createErrorResponse(String message, HttpStatus status) {
        String errorMessage = message != null ? message : "Error interno";
        ResponseObject response = ResponseObject.builder()
                .success(false)
                .message(errorMessage)
                .response(null)
                .build();
        return new ResponseEntity<>(response, status);
    }

}
