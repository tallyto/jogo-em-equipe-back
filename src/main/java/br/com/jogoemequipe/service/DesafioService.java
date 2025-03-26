package br.com.jogoemequipe.service;

import br.com.jogoemequipe.dto.DesafioDTO;
import br.com.jogoemequipe.dto.TarefaDTO;
import br.com.jogoemequipe.model.Desafio;
import br.com.jogoemequipe.model.Tarefa;
import br.com.jogoemequipe.model.Usuario;
import br.com.jogoemequipe.repository.DesafioRepository;
import br.com.jogoemequipe.repository.TarefaRepository;
import br.com.jogoemequipe.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class DesafioService {

    private final DesafioRepository desafioRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    public Desafio criarDesafio(DesafioDTO dto, UUID usuarioId) {
        Usuario criador = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Desafio desafio = new Desafio();
        desafio.setNome(dto.getNome());
        desafio.setDescricao(dto.getDescricao());
        desafio.setCriadoPor(criador);  // Associando o usuário logado como criador

        return desafioRepository.save(desafio);
    }


    @Transactional
    public Desafio adicionarUsuarioAoDesafio(UUID desafioId, UUID usuarioId) {
        Desafio desafio = desafioRepository.findById(desafioId)
                .orElseThrow(() -> new EntityNotFoundException("Desafio não encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        desafio.getParticipantes().add(usuario);
        return desafioRepository.save(desafio);
    }

    public List<Desafio> listarDesafios(UUID usuarioId) {
        return desafioRepository.findByCriadoPorId(usuarioId);  // Método no repositório
    }

    public Desafio buscarDesafioPorId(UUID desafioId, UUID usuarioId) {
        // Verifica se o usuário existe
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // Busca o desafio pelo ID
        Desafio desafio = desafioRepository.findById(desafioId)
                .orElseThrow(() -> new EntityNotFoundException("Desafio não encontrado"));

        // Verifica se o usuário logado é o criador do desafio ou um participante
        if (!desafio.getParticipantes().contains(usuarioId) && !desafio.getCriadoPor().getId().equals(usuarioId)) {
            throw new RuntimeException("Usuário não tem permissão para acessar este desafio");
        }

        return desafio;  // Retorna o desafio se encontrado e com permissão
    }

    public Tarefa criarTarefa(UUID desafioId, TarefaDTO dto, UUID usuarioCriadorId) {
        Desafio desafio = desafioRepository.findById(desafioId).orElseThrow(() -> new RuntimeException("Desafio não encontrado"));
        Usuario usuarioCriador = usuarioRepository.findById(usuarioCriadorId).orElseThrow(() -> new RuntimeException("Usuário criador não encontrado"));

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPontos(dto.getPontos());
        tarefa.setStatus("pendente"); // Defina um status inicial
        tarefa.setDesafio(desafio);
        tarefa.setUsuarioCriador(usuarioCriador);

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefasDoDesafio(UUID desafioId) {
        Optional<Desafio> desafioOptional = desafioRepository.findById(desafioId);
        return desafioOptional.map(tarefaRepository::findByDesafio).orElse(List.of());
    }


    public List<Desafio> listarDesafiosAtivos(UUID usuarioId) {
        return desafioRepository.findByParticipantesIdOrCriadoPor(usuarioId);
    }
}
