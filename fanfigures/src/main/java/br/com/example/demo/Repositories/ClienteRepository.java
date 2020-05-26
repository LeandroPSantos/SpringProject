package br.com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.demo.Models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	boolean existsByCodigo(int codigo);
	boolean existsByEmail(String email);
    Cliente findByEmail(String email);
	
	Cliente findByEmailAndAtivoTrue(String email);
	
	@Query("select distinct p.nome from Cliente u inner join u.grupos g inner join g.permissoes p where u = ?1")
	List<String> listaPermissoes(Cliente u);
}
