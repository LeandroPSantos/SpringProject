package br.com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.demo.Models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
