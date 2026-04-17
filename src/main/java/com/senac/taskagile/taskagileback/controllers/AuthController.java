package com.senac.taskagile.taskagileback.controllers;


import com.senac.taskagile.taskagileback.model.DTO.LoginRequest;
import com.senac.taskagile.taskagileback.model.DTO.LoginResponse;
import com.senac.taskagile.taskagileback.model.repository.UsuarioRepository;
import com.senac.taskagile.taskagileback.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(description = "Serviço responsavel por controlar a autentição de usuario e sessão!", name = "Serviço de autenticação")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(description = "Valida senha asdasad 50 caracteres, calcula a longitude", summary = "Login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){


        if(loginRequest.email().equals("String@s") && loginRequest.senha().equals("String@s")){

            var token = tokenService.gerarToken(loginRequest.email());


            return ResponseEntity.ok(new LoginResponse(token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


    }

}
