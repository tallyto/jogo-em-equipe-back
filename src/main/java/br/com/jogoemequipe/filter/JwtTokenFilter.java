package br.com.jogoemequipe.filter;

import br.com.jogoemequipe.service.JwtTokenService;
import br.com.jogoemequipe.service.UserService;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserService userService;

    @Autowired
    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
            String token = request.getHeader("Authorization");
            token = jwtTokenService.formatToken(token);

            if (token != null && jwtTokenService.validateToken(token)) {
                DecodedJWT decodedJWT = jwtTokenService.decodedJWT(token);
                UserDetails user = userService.loadUserByUsername(decodedJWT.getSubject());
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        } catch (TokenExpiredException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

    }
}
