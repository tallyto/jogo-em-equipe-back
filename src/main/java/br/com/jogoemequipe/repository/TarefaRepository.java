package br.com.jogoemequipe.repository;

import br.com.jogoemequipe.model.Desafio;
import br.com.jogoemequipe.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByDesafio(Desafio desafio);
}

