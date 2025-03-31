package br.com.jogoemequipe.service;

import br.com.jogoemequipe.model.PontosUsuario;
import br.com.jogoemequipe.repository.PontosUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PontosUsuarioService {
    @Autowired
    private PontosUsuarioRepository pontosUsuarioRepository;

    public Integer pontosPorDesafio(UUID idUsuario, UUID idDesafio) {
        return pontosUsuarioRepository.findByUsuarioIdAndDesafioId(idUsuario, idDesafio)
                .map(PontosUsuario::getPontos)
                .orElse(0);
    }


}
