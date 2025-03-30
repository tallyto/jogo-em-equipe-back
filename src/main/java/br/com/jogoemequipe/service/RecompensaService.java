package br.com.jogoemequipe.service;

import br.com.jogoemequipe.dto.RecompensaDTO;
import br.com.jogoemequipe.model.Desafio;
import br.com.jogoemequipe.model.Recompensa;
import br.com.jogoemequipe.model.PontosUsuario;
import br.com.jogoemequipe.repository.DesafioRepository;
import br.com.jogoemequipe.repository.RecompensaRepository;
import br.com.jogoemequipe.repository.PontosUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecompensaService {

    private final RecompensaRepository recompensaRepository;
    private final DesafioRepository desafioRepository;
    private final PontosUsuarioRepository pontosUsuarioRepository;

    public Recompensa criarRecompensa(RecompensaDTO dto) {
        Desafio desafio = desafioRepository.findById(dto.getDesafioId())
                .orElseThrow(() -> new EntityNotFoundException("Desafio não encontrado"));

        Recompensa recompensa = new Recompensa();
        recompensa.setNome(dto.getNome());
        recompensa.setCustoPontos(dto.getCustoPontos());
        recompensa.setDesafio(desafio);
        recompensa.setStatus("PENDENTE");
        recompensa.setResgatada(false); // Inicialmente, a recompensa não foi resgatada
        return recompensaRepository.save(recompensa);
    }

    public List<Recompensa> listarRecompensas(UUID desafioId) {
        return recompensaRepository.findByDesafioId(desafioId);
    }

    @Transactional
    public void resgatarRecompensa(UUID idRecompensa, UUID idUsuario) {
        Recompensa recompensa = recompensaRepository.findById(idRecompensa)
                .orElseThrow(() -> new EntityNotFoundException("Recompensa não encontrada com o ID: " + idRecompensa));

        if (recompensa.isResgatada()) {
            throw new IllegalStateException("Esta recompensa já foi resgatada.");
        }

        Desafio desafio = recompensa.getDesafio();
        Optional<PontosUsuario> pontosUsuarioOptional = pontosUsuarioRepository.findByUsuarioIdAndDesafioId(idUsuario,
                desafio.getId());

        if (pontosUsuarioOptional.isEmpty()) {
            throw new IllegalStateException("O usuário não possui pontos neste desafio.");
        }

        PontosUsuario pontosUsuario = pontosUsuarioOptional.get();

        if (pontosUsuario.getPontos() < recompensa.getCustoPontos()) {
            throw new IllegalStateException("Pontos insuficientes para resgatar a recompensa.");
        }

        pontosUsuario.setPontos(pontosUsuario.getPontos() - recompensa.getCustoPontos());
        pontosUsuarioRepository.save(pontosUsuario);

        recompensa.setStatus("RESGATADA");
        recompensa.setResgatada(true);
        recompensaRepository.save(recompensa);

        // Opcional: Criar um registro no histórico de recompensas resgatadas
    }
}