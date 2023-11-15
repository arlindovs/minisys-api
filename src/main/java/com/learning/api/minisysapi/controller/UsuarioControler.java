package com.learning.api.minisysapi.controller;

import com.learning.api.minisysapi.dto.NewResourceDTO;
import com.learning.api.minisysapi.dto.UsuarioDTO;
import com.learning.api.minisysapi.entity.UsuarioEntity;
import com.learning.api.minisysapi.repository.UsuarioRepository;
import com.learning.api.minisysapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "http://localhost:4200/")
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

        UsuarioEntity newUsuario = this.usuarioService.addNewUsuario(
                usuarioDTO.getName(), usuarioDTO.getEmail(), usuarioDTO.getPassword());

        return new ResponseEntity<>(new NewResourceDTO(newUsuario.getGuid()), HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        if(!StringUtils.hasText(usuarioDTO.getGuid())){
            throw new IllegalArgumentException("Usuário não pode ser null ou vazio");
        }

        this.usuarioService.updateUsuario(
                usuarioDTO.getGuid(),
                usuarioDTO.getName(),
                usuarioDTO.getEmail(),
                usuarioDTO.getPassword());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "{guid}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String guid) {

        this.usuarioService.deleteUsuario(guid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*
    *  Controller para login de usuário
    */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UsuarioEntity userData){
            System.out.println(userData);
        UsuarioEntity user = usuarioRepository.findByName(userData.getName());
        if(user.getPassword().equals(userData.getPassword()))
            return ResponseEntity.ok(user);
        else
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }

}
