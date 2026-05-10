package com.senac.taskagile.taskagileback.domain.entities;

import com.senac.taskagile.taskagileback.application.DTO.UsuarioRequest;
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

    public Usuario(UsuarioRequest usuario) {
        this.email = usuario.email();
        this.nome = usuario.nome();
        this.senha = usuario.senha();
        this.role = "ROLE_USER";
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
