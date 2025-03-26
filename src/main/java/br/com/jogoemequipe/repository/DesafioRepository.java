package br.com.jogoemequipe.repository;

import br.com.jogoemequipe.model.Desafio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DesafioRepository extends JpaRepository<Desafio, UUID> {
    List<Desafio> findByCriadoPorId(UUID usuarioId);

    List<Desafio> findByParticipantesIdOrCriadoPor(UUID usuarioId);
}
