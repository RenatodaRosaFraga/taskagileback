package com.senac.taskagile.taskagileback.application.services;

import com.senac.taskagile.taskagileback.application.DTO.LoginRequest;
import com.senac.taskagile.taskagileback.application.DTO.UsuarioAdmRequest;
import com.senac.taskagile.taskagileback.application.DTO.UsuarioRequest;
import com.senac.taskagile.taskagileback.application.DTO.UsuarioResponse;
import com.senac.taskagile.taskagileback.domain.entities.Usuario;
import com.senac.taskagile.taskagileback.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public List<UsuarioResponse> ListarTodos() {
        try{
            return usuarioRepository.findAll()
                    .stream()
                    .map(UsuarioResponse::new)
                    .collect(Collectors.toList());
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

    public UsuarioResponse BuscarUsuarioPorId(Long id) {
        try{
            var usuario = usuarioRepository.findById(id).orElse(null);
            return new UsuarioResponse(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean AterarUsuario(Long id, Usuario usuario) {

        var usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if (usuarioBanco != null) {
            usuarioBanco.setEmail(usuario.getEmail());
            usuarioBanco.setNome(usuario.getNome());
            usuarioBanco.setSenha(usuario.getSenha());
            usuarioBanco.setStatus(usuario.getStatus());


            usuarioRepository.save(usuarioBanco);

            return true;
        }

        return false;
    }

        public Long SalvarUsuario(UsuarioRequest usuario) {
            try {
                return usuarioRepository.save(new Usuario(usuario)).getId();
            }catch (Exception e){
                throw new RuntimeException(e);
            }


    }

    public Long SalvarUsuarioAdm(UsuarioAdmRequest usuario) {
        try {

            if (usuario.secretKey()) {
                return usuarioRepository.save(new Usuario(usuario)).getId();
            }else
            {
                return 0L;
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
