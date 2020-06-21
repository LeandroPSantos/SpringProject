package br.com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.example.demo.Models.Carrinho;
import br.com.example.demo.Models.Cliente;
import br.com.example.demo.Models.DetalhesVenda;
import br.com.example.demo.Models.Endereco;
import br.com.example.demo.Models.Generico;
import br.com.example.demo.Models.UsuarioSistema;
import br.com.example.demo.Models.Venda;
import br.com.example.demo.Repositories.DetalheVendaRepository;
import br.com.example.demo.Repositories.EnderecoRepository;
import br.com.example.demo.Repositories.VendaRepository;

@Controller
@RequestMapping("/Checkout") 
public class CheckoutController {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private DetalheVendaRepository detalheVendaRepository;
	
	@GetMapping
	public ModelAndView checkout(Venda venda) {

		ModelAndView modelAndView = new ModelAndView("carrinhoCompra/Checkout");
		
		ArrayList<Carrinho> carrinhoLista = new ArrayList<>();
		System.out.println("criou lista"); 
		
		if (Generico.carrinho == null)
			System.out.println("ta vazio");
		else	
		    System.out.println("nao ta nulo");	
		
		int precototal = 0;
		if(Generico.carrinho != null ) 
		{
			if(!Generico.carrinho.isEmpty()) 
			{		
				
				for (Carrinho obj : Generico.carrinho) 
				{
					Carrinho objcarrinho = new Carrinho();
					objcarrinho.setId_produto(obj.getId_produto());		
					objcarrinho.setNome_produto(obj.getNome_produto());
					objcarrinho.setQuantidade(obj.getQuantidade());
					objcarrinho.setUrl_imagem(obj.getUrl_imagem());
					objcarrinho.setValor_produto(obj.getValor_produto());
					objcarrinho.setValor_totalProd(obj.getValor_totalProd());
					
					precototal += obj.getValor_totalProd();
					carrinhoLista.add(objcarrinho);
					
				}
			}
		}
			
		Cliente clienteLogado = new Cliente();
		UsuarioSistema xx = ((UsuarioSistema) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		clienteLogado.setCodigo(xx.getUsuario().getCodigo());
		
		modelAndView.addObject("venda", venda);
		modelAndView.addObject("endereco", enderecoRepository.findByIdUsuario(clienteLogado.getCodigo()));
		modelAndView.addObject("carrinho", carrinhoLista);
		modelAndView.addObject("precototal", precototal);
		
		return modelAndView; 
		
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes)
	{
		System.out.println("venda - id endereco: " + venda.getId_endereco() + " forma pagamento: " + venda.getForma_pagamento() + " numero_parcelas: " + venda.getNumero_parcelas());
		
		
		
		ArrayList<DetalhesVenda> detalhesVenda = new ArrayList<>();
		double precototal = 0;
		
		if(Generico.carrinho != null ) 
		{
			if(!Generico.carrinho.isEmpty()) 
			{	
				
				for (Carrinho obj : Generico.carrinho) 
				{
					DetalhesVenda objDetalhes = new DetalhesVenda();
					objDetalhes.setId_produto(obj.getId_produto());		
					objDetalhes.setQuantidade(obj.getQuantidade());
					objDetalhes.setPrecoProduto(obj.getValor_produto());
					
					precototal += obj.getValor_totalProd();
					detalhesVenda.add(objDetalhes);
					
				}
				
				UsuarioSistema xx = ((UsuarioSistema) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				venda.setIdusuario(xx.getUsuario().getCodigo());
				venda.setTotal_compra(precototal);
				
				LocalDate localdate = LocalDate.now();
				venda.setDatacompra(localdate);
				
				UUID uuid = UUID.randomUUID();
				String myRandom = uuid.toString();	
				venda.setNumero_pedido(myRandom.substring(0,15));
				
				venda = vendaRepository.saveAndFlush(venda);
				
				for (DetalhesVenda objdetalhe : detalhesVenda) 
				{
					objdetalhe.setIdvenda(venda.getIdvenda());
					
					detalheVendaRepository.save(objdetalhe);
				}
				
				 Generico.carrinho.clear();				
			}
		}

		attributes.addFlashAttribute("mensagem", "deu tudo certo!!");

		return new ModelAndView("redirect:/inicio");
	}
}
