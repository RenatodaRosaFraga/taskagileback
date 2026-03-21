package com.senac.taskagile.taskagileback.controllers;


import com.senac.taskagile.taskagileback.model.DTO.LoginRequest;
import com.senac.taskagile.taskagileback.model.DTO.LoginResponse;
import com.senac.taskagile.taskagileback.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){


        if(loginRequest.email().equals("String@s") && loginRequest.senha().equals("String@s")){
            return ResponseEntity.ok(new LoginResponse("Sskjaks123"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


    }

}
