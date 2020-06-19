package br.com.example.demo.Models;

public class Carrinho {
	
	private int id_produto;    
    
    private int quantidade;
    
    private String nome_produto;
    
    private double valor_produto;
    
    private double valor_totalProd;
    
    private double valor_totalCarrinho;
    
    private String url_imagem;
    
    public String getUrl_imagem() {
		return url_imagem;
	}

	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
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
	
	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}
	
	public double getValor_produto() {
		return valor_produto;
	}
      
	public void setValor_produto(double valor_produto) {
		this.valor_produto = valor_produto;
	}
	
	public double getValor_totalProd() {
		return valor_totalProd;
	}
      
	public void setValor_totalProd(double valor_totalProd) {
		this.valor_totalProd = valor_totalProd;
	}
	
	public double getValor_totalCarrinho() {
		return valor_totalCarrinho;
	}
      
	public void setValor_totalCarrinho(double valor_totalCarrinho) {
		this.valor_totalCarrinho = valor_totalCarrinho;
	}
}
