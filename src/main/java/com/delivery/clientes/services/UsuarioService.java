package com.delivery.clientes.services;

import com.delivery.clientes.entity.Usuario;
import com.delivery.clientes.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

    private Logger log =  LoggerFactory.getLogger(UsuarioService.class );

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Usuario usuario = usuarioRepository.findByUsername(username);
        System.out.println("usuarios entrante : "+username);
        Usuario usuario = usuarioRepository.findByEmail(username);

        if (usuario == null) {
            log.error("Error en el login: no existe el usuario: " + username);
            throw new UsernameNotFoundException("Error en el login: no existe el usuario: " + username);
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> log.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
