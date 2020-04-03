package br.com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.example.demo.Models.Produto;
import br.com.example.demo.Repositories.ProdutosRepository;

@Controller
@RequestMapping("/produto") //com isso todos os métodos dessa controller começam a partir de /produto
public class ProdutoController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("produto/listaProdutos");

		modelAndView.addObject("produtos", produtosRepository.findAll());

		return modelAndView;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Produto produto) {

		ModelAndView modelAndView = new ModelAndView("produto/CadastroProduto");

		modelAndView.addObject(produto);

		return modelAndView;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes)
	{

		if (result.hasErrors()) 
			return novo(produto);

		produtosRepository.save(produto);

		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!!");

		return new ModelAndView("redirect:/produto/novo");

	}
}
