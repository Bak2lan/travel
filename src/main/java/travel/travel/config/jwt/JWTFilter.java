package travel.travel.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import travel.travel.exception.NotFoundException;
import travel.travel.model.entity.User;
import travel.travel.repository.UserRepository;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public JWTFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                   @NonNull HttpServletResponse response,
                                   @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization!=null&&authorization.startsWith("Bearer ")){
            String token=authorization.substring(7);
            try{
                if (StringUtils.hasText(token)){
                    String email = jwtService.verifyToken(token);
                    User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(String.format("User with email %s not found", email)));
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    null,
                                    user.getAuthorities()
                            )
                    );
                }
            }catch (JWTVerificationException e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            }catch (NotFoundException e){
                response.sendError(HttpServletResponse.SC_NOT_FOUND,e.getMessage());
            }
        }
        filterChain.doFilter(request,response);

    }
}
