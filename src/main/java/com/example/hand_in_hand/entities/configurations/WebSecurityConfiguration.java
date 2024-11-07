package com.example.hand_in_hand.entities.configurations;


import com.example.hand_in_hand.auth.JwtTokenFilter;
import com.example.hand_in_hand.auth.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {
    private JwtTokenFilter jwtTokenFilter;
    private UserDetailsService userDetailsService;
    @Autowired
    public WebSecurityConfiguration(JwtTokenFilter jwtTokenFilter, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenFilter = jwtTokenFilter;
    }
    public void configurePasswordEncoder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF korumasını devre dışı bırakabilirsiniz, özellikle stateless JWT için
                .authorizeRequests(auth -> auth
                        .requestMatchers("/login").permitAll()  // "/login" endpointine herkes erişebilir
                        .anyRequest().authenticated()  // Diğer istekler kimlik doğrulaması gerektirir
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Oturumsuz (stateless) yapılandırma için
                )
                .httpBasic(withDefaults()); // Basic Authentication kullanarak kimlik doğrulama
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    // AuthenticationManager bean tanımlaması
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    // BCryptPasswordEncoder bean tanımlaması
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
