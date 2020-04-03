package br.com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.demo.Models.Produto;
                                                                //tipo da chave primaria
public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

}
