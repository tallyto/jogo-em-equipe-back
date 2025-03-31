package br.com.jogoemequipe.controller;

import br.com.jogoemequipe.model.Usuario;
import br.com.jogoemequipe.service.PontosUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/pontos-usuario")
@RestController
public class PontosUsuarioController {
    @Autowired
    private PontosUsuarioService pontosUsuarioService;

    @GetMapping("/{idDesafio}")
    Integer pontosPorDesafio(@PathVariable UUID  idDesafio) {
        var usuario = getUsuario();

        var result = pontosUsuarioService.pontosPorDesafio(usuario.getId(), idDesafio);

        return result;
    }

    private static Usuario getUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Usuario) authentication.getPrincipal();
        return usuario;
    }
}
