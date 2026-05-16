package com.senac.taskagile.taskagileback.application.DTO;

public record UsuarioAdmRequest(
        String nome,
        String email,
        String senha,
        boolean secretKey
) {

}
