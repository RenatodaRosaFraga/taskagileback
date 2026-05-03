package com.senac.taskagile.taskagileback.presentation;

import com.senac.taskagile.taskagileback.domain.DTO.LoginRequest;
import com.senac.taskagile.taskagileback.domain.entities.Usuario;
import com.senac.taskagile.taskagileback.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public boolean ValidaUsuarioSenha(LoginRequest loginRequest) {
        try {

            return usuarioRepository.existsUsuarioByEmailContainingAndSenha(loginRequest.email(), loginRequest.senha());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Usuario> ListarTodos() {
        try{
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario BuscarUsuarioLogado(Usuario usuario) {

        try{
            return   usuarioRepository.findById(usuario.getId()).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario BuscarUsuarioPorId(Long id) {
        try{
            return usuarioRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean AterarUsuario(Long id, Usuario usuario) {

        var usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if (usuarioBanco != null){
            usuarioBanco.setEmail(usuario.getEmail());
            usuarioBanco.setNome(usuario.getNome());
            usuarioBanco.setSenha(usuario.getSenha());
            usuarioBanco.setStatus(usuario.getStatus());


            usuarioRepository.save(usuarioBanco);

            return true;
        }

        return false;
    }
}