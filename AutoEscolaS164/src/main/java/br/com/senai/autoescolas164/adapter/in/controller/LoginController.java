package br.com.senai.autoescolas164.adapter.in.controller;

import br.com.senai.autoescolas164.adapter.in.controller.request.usuario.DadosLogin;
import br.com.senai.autoescolas164.application.core.domain.Usuario;
import br.com.senai.autoescolas164.config.security.token.TokenService;
import br.com.senai.autoescolas164.config.security.token.dto.DadosTokenJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosLogin dados) {
        var token = new UsernamePasswordAuthenticationToken(
                dados.login(),
                dados.senha()
        );
        Authentication authentication = manager.authenticate(token);
        String tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}