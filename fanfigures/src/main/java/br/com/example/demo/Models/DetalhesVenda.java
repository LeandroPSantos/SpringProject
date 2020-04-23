package br.com.example.demo.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetalhesVenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_detalheVenda;
	
	private int id_venda;
	
    private int id_produto;    
    
    private int quantidade;
 		
	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

    public int getId_detalheVenda() {
		return id_detalheVenda;
	}

	public void setId_detalheVenda(int id_detalheVenda) {
		this.id_detalheVenda = id_detalheVenda;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
