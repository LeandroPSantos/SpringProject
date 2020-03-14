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

import br.com.example.demo.Models.Produtos;
import br.com.example.demo.Repositories.ProdutosRepository;

@Controller
@RequestMapping("/produtos") //com isso todos os métodos dessa controller começam a partir de /produto
public class ProdutoController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@RequestMapping(method=RequestMethod.GET, value = "/CadastroProdutos")
	public ModelAndView CadastroProdutos() 
	{
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Produtos produto) {

		ModelAndView modelAndView = new ModelAndView("produto/CadastroProduto");

		modelAndView.addObject(produto);

		return modelAndView;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Produtos produto, BindingResult result, RedirectAttributes attributes)
	{

		if (result.hasErrors()) 
			return novo(produto);

		produtosRepository.save(produto);

		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!!");

		return new ModelAndView("redirect:/CadastroProdutos");

	}
}
