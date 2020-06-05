package br.com.example.demo.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.example.demo.Models.Cliente;
import br.com.example.demo.Repositories.ClienteRepository;
import br.com.example.demo.Models.UsuarioSistema;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	ClienteRepository clientes;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente cliente = clientes.findByEmailAndAtivoTrue(email);
		if (cliente == null) {
			new UsernameNotFoundException("Usuário ou senha inválidos!");
		}

		return new UsuarioSistema(cliente, getPermissoes(cliente));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Cliente cliente) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		List<String> permissoes = clientes.listaPermissoes(cliente);
		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority("ROLE_" + p.toUpperCase())));
		
		System.out.println(authorities.toString());

		return authorities;
	}

}
