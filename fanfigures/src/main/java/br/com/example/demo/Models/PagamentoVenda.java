package br.com.example.demo.Models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PagamentoVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_pagamentoVenda;

	private int id_venda;
	
	private String nome_cartao;
	
	private String numero_cartao;
	
	private Date data_validade;
	
	private String cvv;
	
	private int quantidade_parcelas;
	
	public int getQuantidade_parcelas() {
		return quantidade_parcelas;
	}

	public void setQuantidade_parcelas(int quantidade_parcelas) {
		this.quantidade_parcelas = quantidade_parcelas;
	}
	
	public int getId_pagamentoVenda() {
		return id_pagamentoVenda;
	}

	public void setId_pagamentoVenda(int id_pagamentoVenda) {
		this.id_pagamentoVenda = id_pagamentoVenda;
	}
	
	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	
	public String getNome_cartao() {
		return nome_cartao;
	}

	public void setNome_cartao(String nome_cartao) {
		this.nome_cartao = nome_cartao;
	}

	public String getNumero_cartao() {
		return numero_cartao;
	}

	public void setNumero_cartao(String numero_cartao) {
		this.numero_cartao = numero_cartao;
	}

	public Date getData_validade() {
		return data_validade;
	}

	public void setData_validade(Date data_validade) {
		this.data_validade = data_validade;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
}
