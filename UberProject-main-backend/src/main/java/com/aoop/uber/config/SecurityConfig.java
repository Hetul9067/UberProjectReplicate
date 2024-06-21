package com.aoop.uber.config;


import com.aoop.uber.model.passenger.JwtTokenBlackList;
import com.aoop.uber.model.passenger.JwtTokenFilter;
import com.aoop.uber.security.JwtAuthenticationEntryPoint;
import com.aoop.uber.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    private JwtTokenBlackList tokenBlackList;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/h2-console/**", "/passenger/signup/**", "/auth/passenger/**").permitAll()
//                                .requestMatchers("").permitAll()
                                .anyRequest().authenticated());

//                                .and().addFilterBefore(new JwtTokenFilter(tokenBlackList), UsernamePasswordAuthenticationFilter.class));
//                .formLogin(form -> form
//                        .loginPage("/auth/passenger/login")
//                        .loginProcessingUrl("/auth/passenger/login")
//                        .defaultSuccessUrl("/passenger/acount"));
        http.headers(frame -> frame.frameOptions(fr -> fr.disable()));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
