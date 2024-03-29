package br.com.health.service.config;

import br.com.health.service.security.JWTAuthenticationFilter;
import br.com.health.service.security.JWTAuthorizationFilter;
import br.com.health.service.security.JWTUtilComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final JWTUtilComponent jwtUtilComponent;

	private static final String[] PUBLIC_MATCHERS = {"/login"};
	private static final String[] SWAGGER_MATCHERS = {"/swagger-resources/*", "*.html", "/api/v1/swagger.json"};

	public SecurityConfig(UserDetailsService userDetailsService,
	                      JWTUtilComponent jwtUtilComponent) {
		this.userDetailsService = userDetailsService;
		this.jwtUtilComponent = jwtUtilComponent;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll();
		http.authorizeRequests().antMatchers(SWAGGER_MATCHERS).hasRole("ADMIN").anyRequest().permitAll();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtilComponent));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtilComponent, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoderPassword());
	}

	@Bean
	public BCryptPasswordEncoder encoderPassword() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}