package br.com.jogoemequipe.service;

import br.com.jogoemequipe.dto.TarefaDTO;
import br.com.jogoemequipe.model.Desafio;
import br.com.jogoemequipe.model.Tarefa;
import br.com.jogoemequipe.model.Usuario;
import br.com.jogoemequipe.model.PontosUsuario;
import br.com.jogoemequipe.repository.TarefaRepository;
import br.com.jogoemequipe.repository.UsuarioRepository;
import br.com.jogoemequipe.repository.DesafioRepository;
import br.com.jogoemequipe.repository.PontosUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DesafioRepository desafioRepository;

    @Autowired
    private PontosUsuarioRepository pontosUsuarioRepository;

    public Tarefa criarTarefa(TarefaDTO tarefaDTO, UUID desafioId, UUID usuarioCriadorId) {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setPontos(tarefaDTO.getPontos());
        tarefa.setStatus("PENDENTE");

        Desafio desafio = desafioRepository.findById(desafioId)
                .orElseThrow(() -> new EntityNotFoundException("Desafio não encontrado com o ID: " + desafioId));
        tarefa.setDesafio(desafio);

        Usuario usuarioCriador = usuarioRepository.findById(usuarioCriadorId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioCriadorId));
        tarefa.setUsuarioCriador(usuarioCriador);

        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public Tarefa concluirTarefa(UUID idTarefa, UUID idUsuario) {
        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com o ID: " + idTarefa));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + idUsuario));

        if (!tarefa.getStatus().equals("PENDENTE")) {
            throw new IllegalStateException("A tarefa com ID: " + idTarefa + " não está pendente ou já foi concluída.");
        }

        tarefa.setStatus("CONCLUIDA");
        tarefa.setUsuarioConcluidor(usuario);
        tarefaRepository.save(tarefa);

        // Atualizar os pontos do usuário no desafio
        Desafio desafio = tarefa.getDesafio();
        Optional<PontosUsuario> pontosUsuarioOptional = pontosUsuarioRepository.findByUsuarioIdAndDesafioId(idUsuario, desafio.getId());

        PontosUsuario pontosUsuario;
        if (pontosUsuarioOptional.isPresent()) {
            pontosUsuario = pontosUsuarioOptional.get();
            pontosUsuario.setPontos(pontosUsuario.getPontos() + tarefa.getPontos());
        } else {
            pontosUsuario = new PontosUsuario();
            pontosUsuario.setUsuario(usuario);
            pontosUsuario.setDesafio(desafio);
            pontosUsuario.setPontos(tarefa.getPontos());
        }
        pontosUsuarioRepository.save(pontosUsuario);

        return tarefa;
    }

    public Optional<Tarefa> buscarTarefaPorId(UUID id) {
        return tarefaRepository.findById(id);
    }

    public Iterable<Tarefa> listarTarefasPorDesafio(UUID desafioId) {
        return tarefaRepository.findByDesafioId(desafioId);
    }
}