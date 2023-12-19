package com.lovelycat.shinkaibackend.config;

import com.lovelycat.shinkaibackend.response.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(registry -> {
            registry.requestMatchers("/creation/**").permitAll()
                    .anyRequest().authenticated();
        });

        httpSecurity.formLogin(login -> {
            login.loginProcessingUrl("/login")
                    .defaultSuccessUrl("/loginSuccess")
                    .failureUrl("/loginFailed")
                    .successHandler((request, response, authentication) -> {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(Result.success(authentication.getCredentials().getClass().getCanonicalName()).toJSONString());
                    })
                    .failureHandler((request, response, exception) -> {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(Result.failed(Result.CODE_ERR_NOT_AUTHORIZED, exception.getMessage()).toJSONString());
                        exception.printStackTrace();
                    });
        });

        return httpSecurity.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
