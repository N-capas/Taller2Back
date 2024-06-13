package com.grupo01.clinica.controller;

import com.grupo01.clinica.domain.dtos.req.UserLoginDTO;
import com.grupo01.clinica.domain.dtos.req.UserRegisterDTO;
import com.grupo01.clinica.domain.dtos.res.GeneralResponse;
import com.grupo01.clinica.domain.dtos.res.TokenDTO;
import com.grupo01.clinica.domain.entities.Token;
import com.grupo01.clinica.domain.entities.User;
import com.grupo01.clinica.service.contracts.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid UserLoginDTO info){

        try {
            //User user = userService.findByIdentifier(info.getIdentifier());
            User user = userService.findByemail(info.getEmail());
            //System.out.println(user);
            if (user == null)
            {
                return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "User not found! 1");
            }

            if (!userService.isPasswordOk(user, info.getPassword())){
                return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "User not found! 2");
            }
            //System.out.println("User: " + user);       //esto mata la aplicacion da una recursion circular infinita
//            Token token = userService.registerToken(user);
//            return ResponseEntity.ok().body(token);
            Token token = userService.registerToken(user);
            return GeneralResponse.getResponse(HttpStatus.OK, new TokenDTO(token));
        } catch (Exception e) {

            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody @Valid UserRegisterDTO data){
        try {
            User user = userService.findByemail(data.getEmail());
            if(user!=null){
                return GeneralResponse.getResponse(HttpStatus.CONFLICT, "User already exists!");
            }
            userService.createUser(data);
            return GeneralResponse.getResponse(HttpStatus.CREATED, "User created successfully!");
        } catch (Exception e) {

            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }
}
