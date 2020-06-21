 package br.com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/inicio").permitAll()
		.antMatchers("/DetalheProduto/**").permitAll()
		.antMatchers("/cliente/novo").permitAll()
		.antMatchers("/cliente/salvar").permitAll()
		.antMatchers("/produto/imagem/**").permitAll()
		.antMatchers("/carrinhoDeCompras").hasAnyRole("CLIENTE")
		.antMatchers("/endereco").hasAnyRole("CLIENTE")	
		.antMatchers("/Checkout").hasAnyRole("CLIENTE")	
		.antMatchers("/Checkout/salvar").hasAnyRole("CLIENTE")	
		.antMatchers("/cliente/MeusDados").hasAnyRole("CLIENTE")	
		.antMatchers("/cliente/salvarEdit").hasAnyRole("CLIENTE")	
		// fica faltando pra cliente logado = minhas compras e dados pessoais
		.antMatchers("/produto/**").hasAnyRole("ADMINISTRADOR")
		.antMatchers("/cliente/Admin/**").hasAnyRole("ADMINISTRADOR")
		.antMatchers("/fornecedor/**").hasAnyRole("ADMINISTRADOR")
		.anyRequest()				
			.authenticated()
			.and()
		.formLogin() // Para colocar o formulário de login quando usamos Spring Security
			.loginPage("/login")// URL para o formulário de login
			.defaultSuccessUrl("/inicio",true)
			.permitAll() // permissão de acesso para todos ao formulário de login
			.and()
		.sessionManagement() // Controla a sessão
			.maximumSessions(1) // O número máximo de sessões simultaneas para o mesmo usuário
			.expiredUrl("/login"); // Chama a página escolhida no caso de exceder o nr. de acessos ao mesmo tempo*/
}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**")
				.antMatchers("/img/**")
				.antMatchers("/css/**")
				.antMatchers("/bootstrap/**")
				.antMatchers("/js/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
