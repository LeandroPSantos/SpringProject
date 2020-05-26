package br.com.example.demo.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Cliente implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int codigo;
    
	@NotBlank(message = "Endereço de e-mail é obrigatório!")
	@Email(message = "Endereço de e-mail inválido!")
    private String email;
    
	@NotBlank(message = "Senha é obrigatório!")
    private String senha;
    
	@NotBlank(message = "Nome é obrigatório!")
    private String nome_cliente;
    
	@NotBlank(message = "CPF é obrigatório!")
    private String CPF;
    
    private String CNPJ;  
    
    @NotNull(message = "Data de nascimento é obrigatória!")
	@Column(name = "data_nascimento", columnDefinition = "DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate Data_nascimento;  
    
    private String Sexo;
    
    @NotBlank(message = "Telefone é obrigatório!")
	private String Telefone;
    
    private String Celular;       
    
	@Column(name = "data_cadastro", columnDefinition = "DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataCadastro; 
	
	private Boolean ativo;
	
	@Size(min = 1, message = "Selecione pelo menos um grupo!")
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "codigo_usuario")
			, inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))
	private List<Grupo> grupos;
    
	
	
	
	
    public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String getDataCadastroFormatada() {
		LocalDate d = this.dataCadastro;  
		return d.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public LocalDate getData_nascimento() {
		return Data_nascimento;
	}
  
	public void setData_nascimento(LocalDate data_nascimento) {
		Data_nascimento = data_nascimento;
	}
	
	public String getDataNascimentoFormatada() {
		LocalDate d = this.Data_nascimento;  
		return d.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
}
