package br.com.example.demo.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Fornecedor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    int codigo;
	
	@NotBlank(message = "CEP é obrigatório!")
    private String cep;
	
	@NotBlank(message = "Nome é obrigatório!")
	private String nome_fornecedor;
	
	@NotBlank(message = "CNPJ é obrigatório!")
    private String cnpj;
	
	@NotBlank(message = "Endereço é obrigatório!")
	private String Endereco;
	
	@NotBlank(message = "Cidade é obrigatório!")
	private String Cidade;
	
	@NotBlank(message = "Estado é obrigatório!")
	private String Estado;
	
	@NotBlank(message = "Telefone é obrigatório!")
	private String Telefone;
	
	@NotBlank(message = "Endereço de e-mail é obrigatório!")
	@Email(message = "Endereço de e-mail inválido!")
	private String email;
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome_fornecedor() {
		return nome_fornecedor;
	}

	public void setNome_fornecedor(String nome_fornecedor) {
		this.nome_fornecedor = nome_fornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}


