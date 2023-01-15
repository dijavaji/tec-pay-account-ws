package ec.com.technoloqie.pay.account.apirest.ws.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFilter;
	
	
	public WebSecurityConfig(UserDetailsService userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
		
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		return http.csrf()
				.disable().authorizeRequests().anyRequest().authenticated()
				.and()
				//.httpBasic().and() comentado por que se tiene personalizado
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(jwtAuthenticationFilter)	//agrego filtros personales
				.addFilterBefore(this.jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}			
	
	
	/*@Bean	//comentado para utilizar el personalizado
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager =new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin") ).roles().build());
		return manager;
	}*/
	 
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
				.and().build();
	}
	
	public static void main(String ...args ) {
		System.out.println("pass: " + new BCryptPasswordEncoder().encode("123456")+"--");
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public JWTAuthorizationFilter getJwtAuthorizationFilter() {
		return jwtAuthorizationFilter;
	}
}

