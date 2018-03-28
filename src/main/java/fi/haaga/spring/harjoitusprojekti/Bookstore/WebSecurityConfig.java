package fi.haaga.spring.harjoitusprojekti.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haaga.spring.harjoitusprojekti.Bookstore.web.UserDetailServiceImpl;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//Change Spring Security configuration to use user entites instead of in-memory users in
	//authentication
	@Autowired
	private UserDetailServiceImpl UserDetailsService;
	
	@Override
	
		protected void configure (HttpSecurity http) throws Exception{
		http
		 	.authorizeRequests().antMatchers("/css/**").permitAll()
		 	.and()
			.authorizeRequests()
			.antMatchers("save", "add", "booklist", "delete/{id}").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/booklist")
					.permitAll()
					.and()
				.logout()
					.permitAll();
				
		
	}
	
	@Autowired
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth.userDetailsService(UserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
			/*.inMemoryAuthentication()
			   .withUser("user").password("password").roles("USER").and()
			   .withUser("admin").password("password").roles("USER", "ADMIN");*/
	}
	

}
