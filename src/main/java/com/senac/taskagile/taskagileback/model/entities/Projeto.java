package com.senac.taskagile.taskagileback.model.entities;

import com.senac.taskagile.taskagileback.model.enuns.EnumStatusProjeto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projeto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String prazo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumStatusProjeto status = EnumStatusProjeto.ATIVO;

    // Adicione este método abaixo para garantir que o status nunca seja nulo antes de salvar
    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = EnumStatusProjeto.ATIVO;
        }
    }
}


