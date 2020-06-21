package br.com.example.demo.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.demo.Models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer>{
	ArrayList<Venda>findByidUsuario(int idusuario);
}
