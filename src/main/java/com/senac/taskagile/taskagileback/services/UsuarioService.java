package com.senac.taskagile.taskagileback.services;

import com.senac.taskagile.taskagileback.model.DTO.LoginRequest;
import com.senac.taskagile.taskagileback.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public boolean ValidaUsuarioSenha(LoginRequest loginRequest){
        try {


            return usuarioRepository.existsUsuarioByEmailContainingAndSenha(loginRequest.email(), loginRequest.senha());

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
