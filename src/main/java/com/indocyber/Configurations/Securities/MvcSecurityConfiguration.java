//package com.indocyber.Configurations.Securities;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class MvcSecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers( "/resources/**").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .loginProcessingUrl("/authenticating") // get this for free from Spring Security
//                .and().logout();
////                .and().exceptionHandling().accessDeniedPage("/account/accessDenied");
//
//        return http.build();
//    }

//    @Bean
//    public AuthenticationManager authManager(
//            HttpSecurity http,
//            PasswordEncoder getPasswordEncoder,
//            UserDetailsService userDetailsService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(getPasswordEncoder)
//                .and()
//                .build();
//    }
//}

