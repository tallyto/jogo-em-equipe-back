package br.com.jogoemequipe.service;

import br.com.jogoemequipe.dto.ChangePasswordDTO;
import br.com.jogoemequipe.dto.TokenDTO;
import br.com.jogoemequipe.dto.request.LoginRequestDTO;
import br.com.jogoemequipe.dto.request.RegisterRequestDTO;
import br.com.jogoemequipe.enums.RoleEnum;
import br.com.jogoemequipe.exception.BadRequestException;
import br.com.jogoemequipe.exception.NotFoundException;
import br.com.jogoemequipe.exception.ResourceNotFoundException;
import br.com.jogoemequipe.exception.UnauthorizedException;
import br.com.jogoemequipe.model.Role;
import br.com.jogoemequipe.model.Usuario;
import br.com.jogoemequipe.repository.RoleRepository;
import br.com.jogoemequipe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;

    public Usuario register(RegisterRequestDTO dto) {
        Usuario existentUser = userRepository.findByEmail(dto.email());
        if (existentUser != null)
            throw new BadRequestException("This email is already taken");

        Role role = roleRepository.findByDescription(RoleEnum.USER);

        Usuario user = Usuario.builder()
                .email(dto.email())
                .name(dto.name())
                .password(new BCryptPasswordEncoder().encode(dto.password()))
                .roles(Set.of(role))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        return userRepository.save(user);
    }

    public TokenDTO login(LoginRequestDTO dto) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        Usuario user = userRepository.findByEmail(dto.email());

        if (user == null)
            throw new ResourceNotFoundException("Email/password incorrect or non-existent");

        return jwtTokenService.createAccessToken(user.getId(), user.getEmail(), user.getRoles());
    }

    public boolean changePassword(ChangePasswordDTO changePasswordRequestDTO) {
        // 1. Authentication and User Retrieval (Enhanced Security)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User is not authenticated");
        }

        String username = authentication.getName();
        Usuario user = userRepository.findByEmail(username);

        if (user == null) {
            throw new NotFoundException("User with email " + username + " not found");
        }

        // 2. Password Validation (Improved Handling)
        if (!new BCryptPasswordEncoder().matches(changePasswordRequestDTO.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect");
        }

        // 3. New Password Validation and Confirmation (Improved)
        String newPassword = changePasswordRequestDTO.getNewPassword();
        String confirmPassword = changePasswordRequestDTO.getConfirmPassword();


        if (!newPassword.equals(confirmPassword)) {
            throw new BadRequestException("New password and confirm password do not match");
        }

        // Enforce password complexity rules here (optional)

        // 4. Password Update (Optional: Consider Transaction Management)
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user); // Update the user in the database

        return true; // Indicate successful password change
    }
}
