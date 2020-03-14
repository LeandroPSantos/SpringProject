package br.com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.demo.Models.Produtos;
                                                                //tipo da chave primaria
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

}
