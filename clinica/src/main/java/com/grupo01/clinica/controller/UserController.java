package com.grupo01.clinica.controller;

import com.grupo01.clinica.domain.dtos.req.RecordDTO;
import com.grupo01.clinica.domain.dtos.req.UserRoleDTO;
import com.grupo01.clinica.domain.dtos.res.GeneralResponse;
import com.grupo01.clinica.domain.entities.Historic;
import com.grupo01.clinica.domain.entities.Role;
import com.grupo01.clinica.domain.entities.User;
import com.grupo01.clinica.service.contracts.HistoryService;
import com.grupo01.clinica.service.contracts.RoleService;
import com.grupo01.clinica.service.contracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final HistoryService historyService;

    public UserController(UserService userService, RoleService roleService, HistoryService historyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.historyService = historyService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMN')")
    public ResponseEntity<GeneralResponse> getAllUsers(){
        try {
            List<User> users = userService.getAllUsers();
            return GeneralResponse.getResponse(HttpStatus.OK, users);
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMN')")
    public ResponseEntity<GeneralResponse> getUser(@RequestParam("id")UUID id){
        try {
            User user = userService.findBiId(id);
            return GeneralResponse.getResponse(HttpStatus.OK, user);
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }

    @PostMapping("/config/user-role")
    @PreAuthorize("hasAnyAuthority('ADMN')")
    public ResponseEntity<GeneralResponse> updateUserRole(@RequestBody UserRoleDTO userRoleDTO){
        try {
            User user = userService.findByemail(userRoleDTO.getEmail());
            if(user == null){
                return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found!");
            }
            Role role = roleService.getRoleById(userRoleDTO.getIdRole());
            if (user.getRoles().contains(role)){
                userService.deleteUserRole(user, role);
                return GeneralResponse.getResponse(HttpStatus.OK, "User role deleted!");
            }
            userService.updateUserRol(user,role );
            return GeneralResponse.getResponse(HttpStatus.OK, "User role updated!");
        } catch (Exception e){
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!" + e.getMessage());
        }
    }

    @PostMapping("/record")
    @PreAuthorize("hasAnyAuthority( 'DCTR', 'ASST')")
    public  ResponseEntity<GeneralResponse>creteHistory(@RequestBody RecordDTO req){
        try {
            User user = userService.findByemail(req.getEmail());
            if(user == null){
                return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found!");
            }
            Historic historic = historyService.createHistory(req, user);
            if(historic == null){
                return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
            }
            userService.updateHistory(user, historic);
            return GeneralResponse.getResponse(HttpStatus.OK, "History created!");
        } catch (Exception e) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");
        }
    }




}
