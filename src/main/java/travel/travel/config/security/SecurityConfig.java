package travel.travel.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import travel.travel.exception.NotFoundException;
import travel.travel.repository.UserRepository;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTFilter jwtFilter;
    private final UserRepository userRepository;

    public SecurityConfig(JWTFilter jwtFilter, UserRepository userRepository) {
        this.jwtFilter = jwtFilter;
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(String.format("User with email %s not found", email)));
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            (requests
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/**"))
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/tour/**", "/sight/**", "/about-kyrgyzstan/**", "/category/**")
                    .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                    .requestMatchers(HttpMethod.POST, "/tour/**", "/sight/**", "/about-kyrgyzstan/**", "/category/**")
                    .hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/tour/**", "/sight/**", "/about-kyrgyzstan/**", "/category/**")
                    .hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/tour/**", "/sight/**", "/about-kyrgyzstan/**", "/category/**")
                    .hasAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated();
        });

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
