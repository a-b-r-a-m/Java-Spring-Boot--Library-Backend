package io.libreria.springboot;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled=true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.inMemoryAuthentication()
//			.withUser("juza")
//			.password(getPasswordEncoder().encode("luza"))
//			.roles("USER")	//ROLE_ doda saamo
//			.and()
//			.withUser("piza")
//			.password(getPasswordEncoder().encode("liza"))
//			.roles("ADMIN")
//			.and()
		auth.userDetailsService(myUserDetailsService)
			.passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("POST").hasRole("ADMIN")
				.antMatchers("PUT").hasRole("ADMIN")
				.antMatchers("DELETE").hasRole("ADMIN")
				.antMatchers("/users/{id}", "/users/{id}/", "/users/{userId}/books", "/users/{userId}/books/")
					.hasAnyRole("ADMIN", "USER")
				.antMatchers("/users/**").hasRole("ADMIN")
				//.antMatchers("/genres/**").hasAnyRole("ADMIN", "USER")
				//.antMatchers("/books/**").hasAnyRole("ADMIN", "USER")	//==.authenticated()
				.antMatchers("/logout").hasAnyRole("ADMIN", "USER")
				.antMatchers("/").permitAll()
			.and()
				.formLogin()
			.and()
				.httpBasic();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
