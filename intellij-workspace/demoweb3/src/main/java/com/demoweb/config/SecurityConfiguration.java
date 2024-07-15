package com.demoweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 인증 설정
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/", "/home").permitAll()
//                        .requestMatchers("/account/**").permitAll()
//                        .requestMatchers("/image/**", "/styles/**").permitAll()
//                        .anyRequest().authenticated())
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .formLogin((formLogin) -> formLogin
//                        .loginPage("/account/login"));

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/board/*write*", "/board/*edit*", "/board/*delete*").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin((formLogin) -> formLogin
                        .loginPage("/account/login"));

        return http.build();

    }
}
