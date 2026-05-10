package com.senac.taskagile.taskagileback.presentation;

import com.senac.taskagile.taskagileback.application.DTO.AlterarStatusRequest;
import com.senac.taskagile.taskagileback.application.DTO.UsuarioRequest;
import com.senac.taskagile.taskagileback.application.DTO.UsuarioResponse;
import com.senac.taskagile.taskagileback.domain.entities.Usuario;
import com.senac.taskagile.taskagileback.domain.repository.UsuarioRepository;
import com.senac.taskagile.taskagileback.application.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios controller", description = "Controladora responsavel por gerenciar os usuarios!")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar todos", description = "Controladora responsavel por gerenciar os usuarios!")
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        var usuarios = usuarioService.ListarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta de usuario por ID", description = "Médoto responsavel por consultar um unico usuario por ID e se não existir retorna null!")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.BuscarUsuarioPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar usuario", description = "Metodo resposavel por criar usuário")
    public ResponseEntity<Long> salvar(@RequestBody UsuarioRequest usuario) {
        return ResponseEntity.ok(usuarioService.SalvarUsuario(usuario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuario", description = "Metodo responsavel por atualizar usuário")
    public ResponseEntity<?> alterarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        var alterarUsuarioResult = usuarioService.AterarUsuario(id, usuario);
        return alterarUsuarioResult ? ResponseEntity.ok("Atualizado com sucesso!") : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/AlterarStatus")
    public ResponseEntity<?> AlterarStatus(@PathVariable Long id, @RequestBody AlterarStatusRequest statusRequest) {
        var usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if (usuarioBanco != null) {
            usuarioBanco.setStatus(statusRequest.status());
            usuarioRepository.saveAndFlush(usuarioBanco);
            return ResponseEntity.ok("atualizado com sucesso!");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuariologado")
    @Operation(summary = "Consulta usuario logado", description = "busca usuario da sessãoo")
    public ResponseEntity<Usuario> buscarUsarioLogado(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok(usuarioService.BuscarUsuarioLogado(usuario));
    }
}