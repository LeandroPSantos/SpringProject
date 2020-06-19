package br.com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.example.demo.Models.Carrinho;
import br.com.example.demo.Models.Generico;

@Controller
@RequestMapping("/carrinhoDeCompras") 
public class CarrinhoCompraController {
	
	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("carrinhoCompra/CarrinhoDeCompra");
		System.out.println("voltou pra ca"); 
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
			
		modelAndView.addObject("carrinho", carrinhoLista);
		modelAndView.addObject("precototal", precototal);
		
		return modelAndView;
		
	}
	
	@PostMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable int id, RedirectAttributes attributes) {

		System.out.println("chegou aqui na exclusao");
		for (Carrinho obj : Generico.carrinho) 
		{
			System.out.println("entrou no for exclusao");
			if(obj != null) 
			{			
				System.out.println("objeto não null");

				if(obj.getId_produto() == id) 
				{
					System.out.println("achou o id na lista");
					Generico.carrinho.remove(obj);
					System.out.println("excluiu");
					break;
				}
			}
			else
				System.out.println("objetonull");
		}
 
		return "redirect:/carrinhoDeCompras";
	}
}
