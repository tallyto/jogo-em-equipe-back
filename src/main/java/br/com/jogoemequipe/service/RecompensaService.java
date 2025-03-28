package br.com.jogoemequipe.service;

import br.com.jogoemequipe.dto.RecompensaDTO;
import br.com.jogoemequipe.model.Desafio;
import br.com.jogoemequipe.model.Recompensa;
import br.com.jogoemequipe.repository.DesafioRepository;
import br.com.jogoemequipe.repository.RecompensaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecompensaService {

    private final RecompensaRepository recompensaRepository;
    private final DesafioRepository desafioRepository;

    public Recompensa criarRecompensa(RecompensaDTO dto) {
        Desafio desafio = desafioRepository.findById(dto.getDesafioId())
                .orElseThrow(() -> new EntityNotFoundException("Desafio não encontrado"));

        Recompensa recompensa = new Recompensa();
        recompensa.setNome(dto.getNome());
        recompensa.setCustoPontos(dto.getCustoPontos());
        recompensa.setDesafio(desafio);

        return recompensaRepository.save(recompensa);
    }

    public List<Recompensa> listarRecompensas(UUID desafioId) {
        return recompensaRepository.findByDesafioId(desafioId);
    }
}
