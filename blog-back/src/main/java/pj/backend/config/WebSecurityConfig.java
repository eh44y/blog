package pj.backend.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import pj.backend.provider.JwtAccessDeniedHandler;
import pj.backend.provider.JwtAuthenticationEntryPoint;
import pj.backend.provider.JwtFilter;
import pj.backend.provider.JwtProvider;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class WebSecurityConfig {
  private final JwtProvider jwtProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    JwtFilter jwtFilter = new JwtFilter(jwtProvider);
    httpSecurity
        .httpBasic(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .sessionManagement(sessionManagement -> 
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(
            handling -> {
              handling.authenticationEntryPoint(jwtAuthenticationEntryPoint);
              handling.accessDeniedHandler(jwtAccessDeniedHandler);
            })
        .authorizeHttpRequests(requests -> {
          requests.requestMatchers("/", "/api/auth/**", "/api/search/**", "/file/**").permitAll();
          requests.requestMatchers(HttpMethod.GET, "/api/user/**", "/api/board/**").permitAll();
            requests.anyRequest().authenticated();
        })
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    
    return httpSecurity.build();
  } // securityFilterChain

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  } // corsConfigurationSource
} // WebSecurityConfig
