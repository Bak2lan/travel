package travel.travel.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    public JWTFilter(JWTService jwtService) {
        this.jwtService = jwtService;
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
                    filterChain.doFilter(request,response);
                }
            }catch (JWTVerificationException e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token");
            }
        }else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
