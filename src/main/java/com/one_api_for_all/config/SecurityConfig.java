package com.one_api_for_all.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("userA").password("{noop}password").roles("A").build());
        manager.createUser(User.withUsername("userB").password("{noop}password").roles("B").build());
        manager.createUser(User.withUsername("userC").password("{noop}password").roles("C").build());

        /*manager.createUser(User.withDefaultPasswordEncoder().username("userA").password("test").roles("A").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("userB").password("test").roles("B").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("userC").password("test").roles("C").build());*/
        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/employees").hasAnyRole("A", "B", "C")
                        .anyRequest().authenticated()
                )
                .formLogin((form -> form
                        .loginPage("/login")
                        .permitAll())
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }
}
