package com.demoweb.config;

import com.demoweb.security.DemoWebPasswordEncoder;
import com.demoweb.security.DemoWebUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 1 - 1.
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/", "/home").permitAll()
//                        .requestMatchers("/account/**").permitAll()
//                        .requestMatchers("/image/**", "/styles/**").permitAll()
//                        .anyRequest().authenticated())
//                // .httpBasic(Customizer.withDefaults())
//                .httpBasic(AbstractHttpConfigurer::disable)
//                // .formLogin(Customizer.withDefaults());
//                .formLogin((formLogin) -> formLogin.loginPage("/account/login"));

        // 1 - 2.
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/board/*write*", "/board/*edit*", "/board/*delete*").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().permitAll())
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .formLogin((formLogin) -> formLogin.loginPage("/account/login"));

        // 2 - 1.
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/board/*write*", "/board/*edit*", "/board/*delete*").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().permitAll())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(AbstractHttpConfigurer::disable);

//        // 2 - 2.
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/board/*write*", "/board/*edit*", "/board/*delete*").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().permitAll())
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .formLogin(Customizer.withDefaults());

        // 3.
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/board/*write*", "/board/*edit*", "/board/*delete*").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin((login) -> login
                        .loginPage("/account/login")
                        .usernameParameter("memberId")  // default : username -> custom : memberId
                        .passwordParameter("passwd")    // default : password -> custom : passwd
                        .loginProcessingUrl("/account/process-login"))  // spring security가 로그인 처리하는 경로
                .logout((logout) -> logout
                        .logoutUrl("/account/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/home"));


        return http.build();

    }

///////////////////////////////////////////////

//    // PasswordEncoder for 2 - 1, 2 - 2, 2 - 3
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    // 2 - 1. 위의 securityFilterChain()의 2-1, 2-2와 연결되는 테스트
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

//    // 2 - 2. 위의 securityFilterChain()의 2-1, 2-2와 연결되는 테스트
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource); // 미리 정해진 테이블(users, authorities), SQL을 사용해서 인증 처리
//    }

//    // 2 - 3. 위의 securityFilterChain()의 2-1, 2-2와 연결되는 테스트
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager(dataSource);
//        // 사용자 정의 테이블을 사용하기 위해 로그인, 권한 검사에 사용할 Query 지정
//        userDetailsService.setUsersByUsernameQuery("select email,password,enabled " +
//                "from custom_users " +
//                "where email = ?");
//        userDetailsService.setAuthoritiesByUsernameQuery("select email, authority " +
//                "from custom_authorities " +
//                "where email = ?");
//
//        return userDetailsService;
//    }

    // 3.
    @Bean PasswordEncoder passwordEncoder() {
        return new DemoWebPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new DemoWebUserDetailsService();
    }
}
