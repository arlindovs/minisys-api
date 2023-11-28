package com.learning.api.minisysapi.controller.usuario;

import com.learning.api.minisysapi.dto.usuario.AuthenticationDTO;
import com.learning.api.minisysapi.entity.usuario.UsuarioEntity;
import com.learning.api.minisysapi.repository.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.name(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }


        @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UsuarioEntity userData){
            System.out.println(userData);
        UsuarioEntity user = usuarioRepository.findByName(userData.getName());
        if(user.getPassword().equals(userData.getPassword()))
            return ResponseEntity.ok(user);
        else
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }

}
