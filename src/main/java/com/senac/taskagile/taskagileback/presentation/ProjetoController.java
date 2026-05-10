package com.senac.taskagile.taskagileback.presentation;

import com.senac.taskagile.taskagileback.domain.entities.Projeto;
import com.senac.taskagile.taskagileback.domain.repository.ProjetoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@Tag(description = "Serviço para gestão de projetos simplificados", name = "Serviço de Projetos")
public class ProjetoController {

    private final ProjetoRepository projetoRepository;

    public ProjetoController(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarTodos() {
        return ResponseEntity.ok(projetoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id) {
        return projetoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Long> salvar(@RequestBody Projeto projeto) {
        // Retorna o ID gerado para o Front-end confirmar a criação
        return ResponseEntity.ok(projetoRepository.save(projeto).getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @RequestBody Projeto projetoAtualizado) {
        return projetoRepository.findById(id)
                .map(projeto -> {
                    projeto.setNome(projetoAtualizado.getNome());
                    projeto.setPrazo(projetoAtualizado.getPrazo());
                    // projeto.setStatus(...) se quiser mudar o status também
                    Projeto salvo = projetoRepository.save(projeto);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}