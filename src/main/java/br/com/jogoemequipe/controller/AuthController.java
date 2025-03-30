package br.com.jogoemequipe.controller;

import br.com.jogoemequipe.dto.TokenDTO;
import br.com.jogoemequipe.dto.request.LoginRequestDTO;
import br.com.jogoemequipe.dto.request.RegisterRequestDTO;
import br.com.jogoemequipe.dto.response.UserResponseDTO;
import br.com.jogoemequipe.mappers.UserMapper;
import br.com.jogoemequipe.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO dto) {
        authService.register(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginRequestDTO dto) {

        TokenDTO tokenDTO = authService.login(dto);

        return ResponseEntity.ok(tokenDTO);
    }
}
