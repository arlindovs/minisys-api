package com.learning.api.minisysapi.service.usuario;

import com.learning.api.minisysapi.repository.usuario.UsuarioLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioLoginRepository usuarioLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioLoginRepository.findByName(username);
    }
}
