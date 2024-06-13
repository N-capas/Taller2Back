package com.grupo01.clinica.service.impl;

import com.grupo01.clinica.domain.dtos.req.UserRegisterDTO;
import com.grupo01.clinica.domain.entities.Historic;
import com.grupo01.clinica.domain.entities.Role;
import com.grupo01.clinica.domain.entities.Token;
import com.grupo01.clinica.domain.entities.User;
import com.grupo01.clinica.repositorie.TokenRepository;
import com.grupo01.clinica.repositorie.UserRepository;
import com.grupo01.clinica.service.contracts.RoleService;
import com.grupo01.clinica.service.contracts.UserService;
import com.grupo01.clinica.utils.JWTTools;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final JWTTools jwtTools;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;


    public UserServiceImpl(JWTTools jwtTools, TokenRepository tokenRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, RoleService roleService) {
        this.jwtTools = jwtTools;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(() -> new Exception());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) throws Exception {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if(!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });

    }

    @Override
    public User findByemail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Boolean isPasswordOk(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User findUserAuthenticated() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createUser(UserRegisterDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User  newUser = modelMapper.map(user, User.class);
        userRepository.save(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void updateUserRol(User user, Role role) {
       user.getRoles().add(role);
       userRepository.save(user);
    }

    @Override
    public List<User> getAllUsersByRole(List<Role> roles) {
        //return userRepository.findAllByRoles(roles);
        return null;
    }

    @Override
    public void updateHistory(User user, Historic history) {
        user.getHistorics().add(history);
        userRepository.save(user);
    }

    @Override
    public User findBiId(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserRole(User user, Role role) {
        user.getRoles().remove(role);
        userRepository.save(user);
    }
}
