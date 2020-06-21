package br.com.example.demo.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.demo.Models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	ArrayList<Endereco>findByIdUsuario(int idusuario);
}
