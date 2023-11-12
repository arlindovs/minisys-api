package com.learning.api.minisysapi.service;

import com.learning.api.minisysapi.entity.UsuarioEntity;
import com.learning.api.minisysapi.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity addNewUsuario(String nome, String senha){
        if(!StringUtils.hasText(nome)){
            throw new IllegalArgumentException("O nome do usuario não pode ficar vazio");
        }

        UsuarioEntity newUsuario = new UsuarioEntity();
        newUsuario.setGuid(UUID.randomUUID().toString());
        newUsuario.setNome(nome);
        newUsuario.setSenha(senha);

        log.debug("Adding a new usuario with name [ name = {} ]", nome);

        return this.usuarioRepository.save(newUsuario);
    }

    public List<UsuarioEntity> findAllUsuarios(){
        log.debug("Finding all Usuarios");

        return this.usuarioRepository.findAll();
    }
}
