package com.application.puranh.config;


import com.application.puranh.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebSecurity
@EnableSwagger2
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/registration", "/api/login").permitAll()
                .antMatchers("/v2/api-docs","/swagger-resources/*", "*.html", "/api/v1/swagger.json")
                .authenticated()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
//                    .and()
//                .formLogin()
//                    .loginPage("/swagger-ui.html")
////                    .defaultSuccessUrl("/swagger-ui.html", true)
////                    .failureUrl("/login.html?error=true")
//                    .and()
//                .rememberMe()
//                    .and()
//                .logout()
//                    .logoutUrl("/api/logout")
//                    .logoutSuccessUrl("/api/login")
//                .deleteCookies("JSESSIONID");

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
