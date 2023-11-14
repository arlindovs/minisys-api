package com.learning.api.minisysapi.service;

import com.learning.api.minisysapi.entity.UsuarioEntity;
import com.learning.api.minisysapi.exception.ResourceNotFoundException;
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

    public UsuarioEntity addNewUsuario(String name, String email, String password){
        if(!StringUtils.hasText(name) ||
                !StringUtils.hasText(email) ||
                !StringUtils.hasText(password)){
            throw new IllegalArgumentException("Parametros fornecidos não podem ser null ou vazios");
        }

        UsuarioEntity newUsuario = new UsuarioEntity();
        newUsuario.setGuid(UUID.randomUUID().toString());
        newUsuario.setName(name);
        newUsuario.setEmail(email);
        newUsuario.setPassword(password);

        log.debug("Adding a new usuario with name [ name = {} ]", name);

        return this.usuarioRepository.save(newUsuario);
    }

    public UsuarioEntity updateUsuario(String guid, String name, String email, String password){
        if(!StringUtils.hasText(guid) ||
                !StringUtils.hasText(name) ||
                !StringUtils.hasText(email) ||
                !StringUtils.hasText(password)){
            throw new IllegalArgumentException("Parametros fornecidos não podem ser null ou vazios");
        }

        UsuarioEntity retrievedUsuario = this.usuarioRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        retrievedUsuario.setName(name);
        retrievedUsuario.setEmail(email);
        retrievedUsuario.setPassword(password);

        log.debug("Updating usuario with guid [ guid = {}, newName = {}, newEmail = {}, newPassword = {} ]", guid, name, email, password);

        return this.usuarioRepository.save(retrievedUsuario);
    }

    public void deleteUsuario(String guid){
        if(!StringUtils.hasText(guid)){
            throw new IllegalArgumentException("Parametros fornecidos não podem ser null ou vazios");
        }

        UsuarioEntity retrievedUsuario = this.usuarioRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );

        log.debug("Deleting usuario with guid [ guid = {} ]", guid);

        this.usuarioRepository.delete(retrievedUsuario);
    }

    public List<UsuarioEntity> findAllUsuarios(){
        log.debug("Finding all Usuarios");

        return this.usuarioRepository.findAll();
    }

    public UsuarioEntity findUsuarioByGuid(String guid){
        if(!StringUtils.hasText(guid)){
            throw new IllegalArgumentException("Parametros fornecidos não podem ser null ou vazios");
        }

        log.debug("Finding usuario with guid [ guid = {} ]", guid);

        return this.usuarioRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado")
        );
    }

}
