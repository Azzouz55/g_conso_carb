package com.pfe.gestioncarburant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.pfe.gestioncarburant.services.AppUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("mourad").password("mourad").roles("ADMIN");
		auth.userDetailsService(appUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().accessDeniedPage("/faces/login/accessDenied.xhtml").and().authorizeRequests()
				.antMatchers("/faces/admin/**").access("hasRole('Administrateur')").and().authorizeRequests()
				.antMatchers("/faces/views/**").authenticated().and().formLogin().loginProcessingUrl("/appLogin")
				.loginPage("/faces/login/login.xhtml").defaultSuccessUrl("/faces/views/Index.xhtml")
				.failureUrl("/faces/login/login.xhtml?error=1").usernameParameter("username")
				.passwordParameter("password").and().logout().logoutUrl("/appLogout")
				.logoutSuccessUrl("/faces/login/login.xhtml").and().csrf().disable();
	}
}
