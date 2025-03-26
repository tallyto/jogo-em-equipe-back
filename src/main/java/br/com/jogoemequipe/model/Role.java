package br.com.jogoemequipe.model;

import br.com.jogoemequipe.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    private RoleEnum description;

    @Override
    public String getAuthority() {
        return "ROLE_" + description.toString();
    }
}
