package com.senac.taskagile.taskagileback.domain.entities;

import com.senac.taskagile.taskagileback.application.DTO.UsuarioRequest;
import com.senac.taskagile.taskagileback.application.DTO.UsuarioAdmRequest;
import com.senac.taskagile.taskagileback.domain.enuns.EnumStatusUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String role;

    private EnumStatusUsuario status = EnumStatusUsuario.ATIVO;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumStatusUsuario getStatus() {
        return status;
    }

    public void setStatus(EnumStatusUsuario status) {
        this.status = status;
    }

    public Usuario(UsuarioRequest usuario) {
        this.email = usuario.email();
        this.nome = usuario.nome();
        this.senha = usuario.senha();
        this.role = "ROLE_USER";
    }

    public Usuario(UsuarioAdmRequest usuario) {
        this.email = usuario.email();
        this.nome = usuario.nome();
        this.senha = usuario.senha();
        this.role = "ROLE_ADM";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // conta nunca é bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // credenciais nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return this.status == EnumStatusUsuario.ATIVO; // só habilita se status for ATIVO
    }
}
