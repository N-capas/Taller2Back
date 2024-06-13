package com.grupo01.clinica.service.contracts;

import com.grupo01.clinica.domain.dtos.req.UserRegisterDTO;
import com.grupo01.clinica.domain.entities.Historic;
import com.grupo01.clinica.domain.entities.Role;
import com.grupo01.clinica.domain.entities.Token;
import com.grupo01.clinica.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;

    User findByemail(String email);
    Boolean isPasswordOk(User user, String password);
    User findUserAuthenticated();
    void createUser(UserRegisterDTO user);
    List<User>getAllUsers();
    void updateUserRol(User user, Role role);
    List<User>getAllUsersByRole(List<Role> roles);
    void updateHistory(User user, Historic history);
    User findBiId(UUID id);
    void deleteUserRole(User user, Role role);
}
