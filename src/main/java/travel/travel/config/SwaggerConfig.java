package travel.travel.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;

@Configuration
public class SwaggerConfig {
    private static final String API_KEY = "JWT";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .addServersItem(new Server().url("https://api.kyrgyz-source.com").description("Production Server"))
            .components(new Components()
                .addSecuritySchemes(API_KEY, createSecurityScheme()))
            .info(new Info()
                .title("Travel API")
                .description("API documentation for the Travel API")
                .version("1.0.0"))
            .security(Collections.singletonList(new SecurityRequirement().addList(API_KEY)));
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
            .name("Authorization")
            .description("Enter your JWT token")
            .type(SecurityScheme.Type.HTTP)
            .scheme("Bearer")
            .bearerFormat("JWT");
    }
}
