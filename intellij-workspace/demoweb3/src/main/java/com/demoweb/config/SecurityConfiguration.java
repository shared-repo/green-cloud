package com.demoweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        // 1 - 1.
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

        // 1 - 2.
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/board/*write*", "/board/*edit*", "/board/*delete*").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().permitAll())
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .formLogin((formLogin) -> formLogin
//                        .loginPage("/account/login"));

        // 2.
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/board/*write*", "/board/*edit*", "/board/*delete*").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();

    }

///////////////////////////////////////////////

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    // 1.
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // System.out.println(passwordEncoder().encode("Pa$$w0rd"));
//        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();
//        userDetailService.createUser(User
//                .withUsername("inmemoryuser")
//                // .password("{noop}Pa$$w0rd")
//                .password(passwordEncoder().encode("Pa$$w0rd"))
//                .roles("USER")
//                .build());
//        userDetailService.createUser(User
//                .withUsername("inmemoryadmin")
//                // .password("{noop}Pa$$w0rd")
//                .password(passwordEncoder().encode("Pa$$w0rd"))
//                .roles("ADMIN")
//                .build());
//
//        return userDetailService;
//
//    }

//    // 2 - 1.
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource); // 미리 정해진 테이블, SQL을 사용해서 인증 처리
//    }

    // 2 - 2.
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager(dataSource);
        // 사용자 정의 테이블을 사용하기 위해 로그인, 권한 검사에 사용할 Query 지정
        userDetailsService.setUsersByUsernameQuery("select email,password,enabled " +
                "from custom_users " +
                "where email = ?");
        userDetailsService.setAuthoritiesByUsernameQuery("select email, authority " +
                "from custom_authorities " +
                "where email = ?");

        return userDetailsService;
    }
}
