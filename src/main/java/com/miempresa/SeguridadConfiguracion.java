package com.miempresa;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.miempresa.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioServicio userDet;
	
	@Autowired
	private BCryptPasswordEncoder bcryp;
	
	@Bean
	public BCryptPasswordEncoder passEncoder() {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		return bcpe;
	}
	
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDet).passwordEncoder(bcryp);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .antMatchers("/", "/listarEmpleados", "/logout-success").permitAll()
	            .antMatchers("/agregarEmpleado").access("hasRole('ROLE_ADMIN')")
	            .antMatchers("/mostrarEmpleado").access("hasRole('ROLE_ADMIN')")
	            .antMatchers("/guardarEmpleado").access("hasRole('ROLE_ADMIN')")
	            .antMatchers("/editarEmpleado").access("hasRole('ROLE_ADMIN')")
	            .antMatchers("/eliminarEmpleado/{id}").access("hasRole('ROLE_ADMIN')")
	            .anyRequest().authenticated()
	        .and()
	        .exceptionHandling()
	            .accessDeniedPage("/error")
	        .and()
	        .formLogin()
	            .loginPage("/login")
	            .permitAll()
	        .and()
	        .logout()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/logout-success")
	            .permitAll();
	}

}

