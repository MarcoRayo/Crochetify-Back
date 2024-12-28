package utez.edu.mx.crochetifyBack.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import utez.edu.mx.crochetifyBack.entities.User;
import utez.edu.mx.crochetifyBack.exceptions.CustomExceptionUnauthorized;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;

import utez.edu.mx.crochetifyBack.repositories.UserRepository;
import utez.edu.mx.crochetifyBack.secutiry.JWTUtils;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseObject login(String email, String password) {

        try {
            User user = userRepository.findByEmail(email);
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new CustomExceptionUnauthorized("Contraseña incorrecta");
            }

            String role = user.getRole().getName().name();
            Long idUser = user.getIdUser();
            String token = jwtUtils.generateAccessToken(email, role,idUser);

            return createResponse("Inicio de sesion exitoso", token);

        } catch (Exception e) {
            throw new CustomExceptionUnauthorized("Credenciales inválidas");
        }
    }

    private ResponseObject createResponse(String message, String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        return ResponseObject.builder()
                .success(true)
                .message(message)
                .response(response)
                .build();
    }

}
