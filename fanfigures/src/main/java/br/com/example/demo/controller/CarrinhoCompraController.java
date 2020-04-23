package br.com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.example.demo.Models.Carrinho;

@Controller
@RequestMapping("/carrinhoDeCompras") 
public class CarrinhoCompraController {
	
	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("carrinhoCompra/CarrinhoDeCompra");
  
		ArrayList<Carrinho> ListaCarrinho = new ArrayList<Carrinho>();
		
		Carrinho carrinho = new Carrinho();
		
		carrinho.setId_produto(1);
		carrinho.setNome_produto("produto 1");
		carrinho.setQuantidade(2);
		carrinho.setValor_produto(15.00);
		carrinho.setValor_totalProd(carrinho.getValor_produto() * carrinho.getQuantidade());
		
		ListaCarrinho.add(carrinho);
			
		Carrinho carrinho2 = new Carrinho();
		carrinho2.setId_produto(2);
		carrinho2.setNome_produto("produto 3"); 
		carrinho2.setQuantidade(1);
		carrinho2.setValor_produto(10.00);
		carrinho2.setValor_totalProd(carrinho2.getValor_produto() * carrinho2.getQuantidade());
		
		ListaCarrinho.add(carrinho2);
		
		modelAndView.addObject("ListaCarrinho", ListaCarrinho);
		//modelAndView.addObject("carrinho", carrinho);

		return modelAndView;
	}
}
