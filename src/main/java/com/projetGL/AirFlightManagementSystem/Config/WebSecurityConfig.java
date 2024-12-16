/*package com.projetGL.AirFlightManagementSystem.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.projetGL.AirFlightManagementSystem.Service.CustomUserDetailsService;


@Configuration 
@EnableWebSecurity

public class WebSecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	@Autowired
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	

	
	public WebSecurityConfig(CustomUserDetailsService customUserDetailsService,
			CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
		super();
		this.customUserDetailsService = customUserDetailsService;
		
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
	}
	 
	



	private final String[] publicUrl = {"/",
	            "/global-search/**",
	            "/register",
	            "/register/**",
	            "/webjars/**",
	            "/resources/**",
	            "/assets/**",
	            "/css/**",
	            "/summernote/**",
	            "/js/**",
	            "/*.css",
	            "/*.js",
	            "/*.js.map",
	            "/fonts**", "/favicon.ico", "/resources/**", "/error"};
	
	




	@Bean
	protected SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http.authorizeHttpRequests(auth ->{
			auth.requestMatchers(publicUrl).permitAll();
			auth.anyRequest().authenticated();
		});
		http.formLogin(form->form.loginPage("/login").permitAll()
				.successHandler(customAuthenticationSuccessHandler))
		        .logout( logout ->{
		        	logout.logoutUrl("/logout");
		        	logout.logoutSuccessUrl("/");
		        }).cors(Customizer.withDefaults())
		           .csrf(csrf->csrf.disable());
		return http.build();	
	     }

	
	
@Bean
	public AuthenticationProvider authenticationProvider() {
	
	
	 DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
     authenticationProvider.setPasswordEncoder(passwordEncoder());
    authenticationProvider.setUserDetailsService(customUserDetailsService);
    return authenticationProvider;
	}





@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}*/
package com.projetGL.AirFlightManagementSystem.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.projetGL.AirFlightManagementSystem.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService,
                             CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }
    
    // Définition des URL publiques accessibles sans authentification
    private final String[] publicUrl = {"/",
            "/global-search/**",
            "/register",
            "/register/**",
            "/webjars/**",
            "/resources/**",
            "/assets/**",
            "/css/**",
            "/summernote/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/fonts**", "/favicon.ico", "/resources/**", "/error"};
    
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuration de l'authentification
        http.authenticationProvider(authenticationProvider());

        // Définir les règles d'autorisation sur les URLs
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(publicUrl).permitAll();
            // Accès réservé aux rôles ADMIN et PASSENGER
            auth.requestMatchers("/admin-dashboard/**").hasRole("ADMIN");
            auth.requestMatchers("/passenger-dashboard/**").hasRole("PASSENGER");
            // Toute autre requête doit être authentifiée
            auth.anyRequest().authenticated();
        });

        // Configuration du login et du logout
        http.formLogin(form -> form
            .loginPage("/login")
            .permitAll()
            .successHandler(customAuthenticationSuccessHandler))
            .logout(logout -> {
                logout.logoutUrl("/logout");
                logout.logoutSuccessUrl("/");
            });

        // Désactivation de CSRF et CORS par défaut
        http.cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // Définir l'AuthenticationProvider avec un DaoAuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        return authenticationProvider;
    }

    // Définir le PasswordEncoder à utiliser pour le hashage des mots de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

