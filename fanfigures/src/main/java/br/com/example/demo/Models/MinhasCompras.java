package br.com.example.demo.Models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

public class MinhasCompras {
	
	private int idVenda;
	
	private int idUsuario;
	
	private LocalDate datacompra;
	
	private String numero_pedido;
	
	private int id_endereco;
	
	private String forma_pagamento;
	
	private int numero_parcelas;
	
	private double total_compra;
	
	private List<Produto> produtos;
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getIdvenda() {
		return idVenda;
	}

	public void setIdvenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getIdusuario() {
		return idUsuario;
	}

	public void setIdusuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDate getDatacompra() {
		return datacompra;
	}

	public void setDatacompra(LocalDate datacompra) {
		this.datacompra = datacompra;
	}

	public String getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(String numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

	public int getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(int id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getForma_pagamento() {
		return forma_pagamento;
	}

	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	
	public int getNumero_parcelas() {
		return numero_parcelas;
	}
  
	public void setNumero_parcelas(int numero_parcelas) {
		this.numero_parcelas = numero_parcelas;
	}
	
	public double getTotal_compra() {
		return total_compra;
	}

	public void setTotal_compra(double total_compra) {
		this.total_compra = total_compra;
	}

}
