package travel.travel.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import travel.travel.exception.TokenExpiredException;
import travel.travel.model.entity.User;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTService {
    private final String secretKey = "Travel";

    public String generateToken(User user) {
        return JWT.create()
                .withClaim("email", user.getEmail())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusDays(10).toInstant()))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String verifyToken(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            return verify.getClaim("email").asString();
        } catch (TokenExpiredException e) {
            throw new RuntimeException("Токен истёк. Авторизуйтесь снова.");
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Неверный токен.");
        }
    }
}