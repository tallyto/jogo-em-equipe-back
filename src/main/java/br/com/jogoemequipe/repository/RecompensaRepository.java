package br.com.jogoemequipe.repository;

import br.com.jogoemequipe.model.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {
    List<Recompensa> findByDesafioId(UUID desafioId);
}
