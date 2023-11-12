package com.learning.api.minisysapi.controller;

import com.learning.api.minisysapi.dto.NewResourceDTO;
import com.learning.api.minisysapi.dto.UsuarioDTO;
import com.learning.api.minisysapi.entity.UsuarioEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.api.minisysapi.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioControler {

    private final UsuarioService usuarioService;

    public UsuarioControler(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> resp = StreamSupport.stream(this.usuarioService.findAllUsuarios().spliterator(), false)
                .map(usuarioEntity -> UsuarioDTO.toDTO(usuarioEntity))
                .collect(Collectors.toList());
        return new ResponseEntity<List<UsuarioDTO>>(resp, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewResourceDTO> addNewUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        UsuarioEntity newUsuario = this.usuarioService.addNewUsuario(usuarioDTO.getNome(), usuarioDTO.getSenha());

        return new ResponseEntity<>(new NewResourceDTO(newUsuario.getGuid()), HttpStatus.CREATED);
    }

}
