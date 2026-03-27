package com.senac.taskagile.taskagileback.model.repository;

import com.senac.taskagile.taskagileback.model.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

}
