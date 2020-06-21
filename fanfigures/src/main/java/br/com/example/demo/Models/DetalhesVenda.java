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
	
	private int idVenda;
	
    private int id_produto;    
    
    private int quantidade;
    
    private double preco_produto; 
    
    public double getPrecoProduto() {
		return preco_produto;
	}

	public void setPrecoProduto(double preco_produto) {
		this.preco_produto = preco_produto;
	}
 		
	public int getIdvenda() {
		return idVenda;
	}

	public void setIdvenda(int idVenda) {
		this.idVenda = idVenda;
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
