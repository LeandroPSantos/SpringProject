package br.com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.demo.Models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
