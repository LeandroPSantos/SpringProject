package br.com.example.demo.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.example.demo.Models.PreCarrinho;
import br.com.example.demo.Models.Produto;
import br.com.example.demo.Models.Carrinho;
import br.com.example.demo.Models.Generico;
import br.com.example.demo.Repositories.ProdutosRepository;

@Controller
@RequestMapping("/DetalheProduto") 
public class DetalheProdutoController {

	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping("/{id}")
	public ModelAndView ExibirDetalheProduto(@PathVariable int id) 
	{
		Produto produto = new Produto();
		produto = produtosRepository.getOne(id);
		
		ModelAndView modelAndView = new ModelAndView("produto/DetalheProduto");

		modelAndView.addObject(produto);
		PreCarrinho precarrinho = new PreCarrinho();
		modelAndView.addObject(precarrinho);
		return modelAndView;
	}
	
	@PostMapping("/AdicionarProdutoCarrinho")
	public String AdicionaProdutoCarrinho(@Valid PreCarrinho precarrinho, BindingResult result, RedirectAttributes attributes) 
	{		
		
		Produto produto = new Produto();
		produto = produtosRepository.getOne(precarrinho.getidProduto());
		
		Carrinho carrinho = new Carrinho();
		carrinho.setId_produto(produto.getId_produto());
		carrinho.setNome_produto(produto.getNome());
		carrinho.setQuantidade(precarrinho.getquantidade());
		carrinho.setValor_produto(produto.getPreco());
		carrinho.setValor_totalProd(produto.getPreco() * precarrinho.getquantidade());
		carrinho.setUrl_imagem(produto.getUrl_imagem());
		//Generico.valorTotalCarrinho += carrinho.getValor_totalProd();
		
		if(Generico.carrinho == null)
			Generico.carrinho = new ArrayList<>();
		else if (!Generico.carrinho.isEmpty()) 
		{
			for (Carrinho obj : Generico.carrinho) 
			{
				if(obj.getId_produto() == precarrinho.getidProduto()) 
				{
					Generico.carrinho.remove(obj);
					break;
				}
			}
		}
		
		Generico.carrinho.add(carrinho);
		
		return "redirect:/carrinhoDeCompras";		
	}
}
