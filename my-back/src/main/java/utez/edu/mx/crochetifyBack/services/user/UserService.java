package utez.edu.mx.crochetifyBack.services.user;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserUpdateStatusRequest;

public interface UserService {

     ResponseObject createUser(UserCreateRequest request);

     ResponseObject updateUser(UserUpdateRequest request);

     ResponseObject updateUserStatus(UserUpdateStatusRequest request);

     ResponseObject getUserById(Long idUser);

     ResponseList getUsers();

     ResponseObject deleteUserbyID(Long idUser);
}
