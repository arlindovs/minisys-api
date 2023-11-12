package com.learning.api.minisysapi.repository;

import com.learning.api.minisysapi.entity.UsuarioEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<UsuarioEntity, Long>, JpaRepository<UsuarioEntity, Long>{

    Optional<UsuarioEntity> findByNome(String nome);

    Optional<UsuarioEntity> findBySenha(String senha);

    Optional<UsuarioEntity> findByGuid(String guid);
}
