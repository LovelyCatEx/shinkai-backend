package com.lovelycat.shinkaibackend.config;

import com.lovelycat.shinkaibackend.response.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // No csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(registry -> registry.requestMatchers("/creation/**").permitAll()
                .requestMatchers("/comment/**").permitAll()
                .requestMatchers("/gallery/**").permitAll()
                .anyRequest().authenticated());

        httpSecurity.formLogin(login -> login.loginProcessingUrl("/login")
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailed")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(Result.success("welcome back").toJSONString());
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(Result.failed(Result.CODE_ERR_NOT_AUTHORIZED, exception.getMessage()).toJSONString());
                    exception.printStackTrace();
                }));

        return httpSecurity.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://127.0.0.1:5173");
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedOrigin("http://sk.lovelycatv.cn");
        configuration.addAllowedOrigin("https://sk.lovelycatv.cn");
        // When using cookies, allowed origins can not be *
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();

        configurationSource.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(configurationSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
