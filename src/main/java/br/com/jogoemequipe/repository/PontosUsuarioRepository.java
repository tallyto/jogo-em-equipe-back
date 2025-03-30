package br.com.jogoemequipe.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jogoemequipe.model.PontosUsuario;

public interface PontosUsuarioRepository extends JpaRepository<PontosUsuario, UUID> {

    Optional<PontosUsuario> findByUsuarioIdAndDesafioId(UUID idUsuario, UUID id);
    
}
