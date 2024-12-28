package travel.travel.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import travel.travel.config.jwt.JWTFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return null;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((request) -> {
            request.requestMatchers("/**")
                    .permitAll();
            request.anyRequest().hasAnyRole("ADMIN")
                    .anyRequest()
                    .authenticated();
        });
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
