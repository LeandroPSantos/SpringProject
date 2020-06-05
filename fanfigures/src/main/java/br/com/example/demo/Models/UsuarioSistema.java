package br.com.example.demo.Models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class UsuarioSistema extends User{
	
	private static final long serialVersionUID = 1L;
	private Cliente cliente;

	public UsuarioSistema(Cliente cliente, Collection<? extends GrantedAuthority> authorities) {
		super(cliente.getEmail(), cliente.getSenha(), authorities);

		this.cliente = cliente;
	}

	public Cliente getUsuario() {
		return cliente;
	}

	public void setUsuario(Cliente cliente) {
		this.cliente = cliente;
	}

}
