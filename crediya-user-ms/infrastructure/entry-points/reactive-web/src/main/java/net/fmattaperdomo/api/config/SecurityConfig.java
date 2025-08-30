package net.fmattaperdomo.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api-docs/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/user/swagger-ui.html",
            "/user/swagger-ui/**",
            "/user/api-docs/**",
            "/user/v3/api-docs/**",
            "/user/webjars/**"
    };
}
