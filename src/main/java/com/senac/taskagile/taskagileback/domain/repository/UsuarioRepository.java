package com.senac.taskagile.taskagileback.domain.repository;

import com.senac.taskagile.taskagileback.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    boolean existsUsuarioByEmailContainingAndSenha(String email, String senha);
}
