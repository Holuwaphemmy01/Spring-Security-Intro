package com.example.SpringSecu.config;

import com.example.SpringSecu.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User
//                .withDefaultPasswordEncoder()
//                .username("akinzo")
//                .password("a@123")
//                .roles("USER")
//                .build();
//
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("victor")
//                .password("v@123")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, user2);
//    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @RestController
    public static class StudentsController {

        private List<Students> students = new ArrayList<>(List.of(new Students(1, "femi", 90),
                new Students(2, "esther", 96))
            );


        @GetMapping("/students")
        public List<Students> getStudents() {
            return students;
        }
    }
}
