package br.com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.demo.Models.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}
