package com.learning.api.minisysapi.entity.usuario;

public enum UsuarioRoleEntity {

    ADMIN("admin"),
    USER("user");

    private String role;

    UsuarioRoleEntity(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
