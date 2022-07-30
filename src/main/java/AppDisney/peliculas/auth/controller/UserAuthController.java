package AppDisney.peliculas.auth.controller;

import AppDisney.peliculas.auth.dto.AuthenticationRequest;
import AppDisney.peliculas.auth.dto.AuthenticationResponse;
import AppDisney.peliculas.auth.dto.UserDTO;
import AppDisney.peliculas.auth.service.JwtUtils;
import AppDisney.peliculas.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO user) throws Exception {
        userDetailsCustomService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> signIn(@Valid @RequestBody AuthenticationRequest request) throws Exception {

        AuthenticationResponse response = userDetailsCustomService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
