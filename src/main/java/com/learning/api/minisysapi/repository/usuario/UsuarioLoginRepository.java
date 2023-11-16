package com.learning.api.minisysapi.repository.usuario;

import com.learning.api.minisysapi.entity.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioLoginRepository extends JpaRepository<UsuarioEntity, Long> {

    UserDetails findByName(String nome);
}
