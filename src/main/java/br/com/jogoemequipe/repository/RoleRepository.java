package br.com.jogoemequipe.repository;

import br.com.jogoemequipe.enums.RoleEnum;
import br.com.jogoemequipe.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByDescription(RoleEnum roleEnum);
}
