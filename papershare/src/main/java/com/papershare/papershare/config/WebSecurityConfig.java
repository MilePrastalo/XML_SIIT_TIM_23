package com.papershare.papershare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.papershare.papershare.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private JwtAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	// configure AuthenticationManager so that it knows from where to load
	// user for matching credentials
	// Use BCryptPasswordEncoder
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	// Defining access rights to specific URLs
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// comunication between client and server is stateless
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				// for unauthorized request send 401 error
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

				// don't authenticate this particular request
				.authorizeRequests().antMatchers("/auth/**").permitAll().antMatchers(HttpMethod.OPTIONS, "/api/**")
				.permitAll()

				.antMatchers(HttpMethod.POST, "/api/user/login", "/api/user/register")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/api/exist/initiateData").permitAll()
				.antMatchers(HttpMethod.GET, "/api/review/**").permitAll()

				// all other requests need to be authenticated
				.anyRequest().authenticated().and();

		// intercept every request and add filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) {
		// TokenAuthenticationFilter will ignore the following
		web.ignoring().antMatchers(HttpMethod.POST, "/login");
		web.ignoring().antMatchers(HttpMethod.POST, "/register");
		web.ignoring().antMatchers(HttpMethod.GET, "/testReview");
		web.ignoring().antMatchers(HttpMethod.POST, "/api/papers");
		web.ignoring().antMatchers(HttpMethod.GET, "/api/exist/initiateData");
		web.ignoring().antMatchers(HttpMethod.GET, "/api/review/**");
	}
}
