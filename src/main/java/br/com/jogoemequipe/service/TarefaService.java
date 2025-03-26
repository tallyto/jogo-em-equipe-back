package br.com.jogoemequipe.service;

import br.com.jogoemequipe.dto.TarefaDTO;
import br.com.jogoemequipe.model.Tarefa;
import br.com.jogoemequipe.model.Usuario;
import br.com.jogoemequipe.repository.TarefaRepository;
import br.com.jogoemequipe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tarefa criarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();


        return tarefaRepository.save(tarefa);
    }
}
