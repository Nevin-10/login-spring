package com.example.__spring_security.security;
//NOW USE USERS FROM SECURITY CONFIGURATION

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration

//Add users, passwords and roles
public class DemoSecurityConfig {

    //To store user details locally in Memory
//ADD SUPPORT FOR JDBC .. No more hardcoded users
    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);

    }

    //Spring Security know exact columns to check, we inject the datasource and return the datasource

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }






//For creating custom Login Page, need to  create a controller(Handles api) for login page that renders the html
    //Automatically provides with another api that handles authentication, no need to create controller but create a post request in HTML file.


@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //Restrict access based on the http request, no access directly divert to login
        http.authorizeRequests(configurer ->
                configurer
                        .requestMatchers("/").hasAnyRole("EMPLOYEE", "ADMIN" ,"MANAGER")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .requestMatchers("/register").permitAll() //Opens /register url

                        .anyRequest().authenticated()
        )

                .formLogin(form ->

                        form.loginPage("/showLoginForm")
                                .loginProcessingUrl("/authenticateTheUser") //To check email and password
                                .permitAll() //Everyone can access the login page,no authorization needed

                )
                .logout(logout -> logout.permitAll())//Expose /logout url for logging out, Spring security gives this.
                .exceptionHandling(configurer->configurer.accessDeniedPage("/accessDenied"));
                //Need to post to logout, no need to define in controller.
        return http.build();




}






























}
