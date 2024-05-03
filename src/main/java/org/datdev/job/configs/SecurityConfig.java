//package org.datdev.job.configs;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.server.ResponseStatusException;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//
//    private static final String[] PUBLIC_URLS = {
//            "/",
//            "/api/**",
//            "/public/**",
//            "/assets/**",
//            "/api-docs/**",
//            "/swagger-ui/index.html",
//            "/swagger-ui/**",
//            "/webjars/**",
//            "/ws/**"
//    };
//
//    private static final String[] PRIVATE_URLS = {
//            "/admin/**",
//    };
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .dispatcherTypeMatchers(PUBLIC_URLS).permitAll() // Allow access to public URLs without authentication
//                .anyRequest().authenticated()
//                .and()
//                .cors()
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler())
//                .and()
//                .csrf().disable();
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        // Allow all origins, you can set specific origins if needed
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    private AccessDeniedHandler accessDeniedHandler() {
//        return (request, response, accessDeniedException) -> {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. You don't have the required role.");
//        };
//    }
//
//}