package br.com.example.demo.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.demo.Models.DetalhesVenda;

public interface DetalheVendaRepository extends JpaRepository<DetalhesVenda, Integer>{
	ArrayList<DetalhesVenda>findByidVenda(int idVenda);
}
