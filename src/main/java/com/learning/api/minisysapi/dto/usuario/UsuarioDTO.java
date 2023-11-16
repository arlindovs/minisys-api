package com.learning.api.minisysapi.dto.usuario;

import com.learning.api.minisysapi.entity.usuario.UsuarioEntity;
import com.learning.api.minisysapi.entity.usuario.UsuarioRoleEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioDTO {

    private String guid;

    @NotBlank(message = "Nome do usuário não pode ser null ou vazio")
    private String name;

    @NotBlank(message = "Nome do usuário não pode ser null ou vazio")
    private String email;

    @NotBlank(message = "Senha não pode ser null ou vazio")
    private String password;

    @NotBlank(message = "Perfil não pode ser null ou vazio")
    private UsuarioRoleEntity role;

    public static UsuarioDTO toDTO(UsuarioEntity usuarioEntity){
        return UsuarioDTO.builder()
                .guid(usuarioEntity.getGuid())
                .name(usuarioEntity.getName())
                .email(usuarioEntity.getEmail())
                .password(usuarioEntity.getPassword())
                .role(usuarioEntity.getRole())
                .build();
    }
}
