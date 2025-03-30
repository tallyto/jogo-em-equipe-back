package br.com.jogoemequipe.repository;

import br.com.jogoemequipe.model.Desafio;
import br.com.jogoemequipe.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
    List<Tarefa> findByDesafio(Desafio desafio);

    Iterable<Tarefa> findByDesafioId(UUID desafioId);
}

