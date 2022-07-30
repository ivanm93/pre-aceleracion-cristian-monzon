package AppDisney.peliculas.auth.service;

import AppDisney.peliculas.auth.dto.AuthenticationRequest;
import AppDisney.peliculas.auth.dto.AuthenticationResponse;
import AppDisney.peliculas.auth.dto.UserDTO;
import AppDisney.peliculas.auth.entity.UserEntity;
import AppDisney.peliculas.auth.repository.UserRepository;
import AppDisney.peliculas.service.EmailService;
import AppDisney.peliculas.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(userName);

        if (userEntity == null) {
            throw new UsernameNotFoundException("Nombre de usuario no encontrado");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDTO userDTO) {
        UserEntity user = userRepository.findByUsername(userDTO.getUsername());
        if(user != null) {
            throw new BadCredentialsException("El nombre de usuario ya está en uso");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity = userRepository.save(userEntity);
        emailService.sendWelcomeEmailTo(userEntity.getUsername());
        return true;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {

        UserDetails userDetails;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Nombre de usuario o contraseña incorrecta", e);
        }
        final String jwt = jwtUtils.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }
}
