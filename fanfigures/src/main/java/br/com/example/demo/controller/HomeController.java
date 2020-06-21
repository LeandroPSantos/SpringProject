package br.com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.example.demo.Models.Produto;
import br.com.example.demo.Repositories.ProdutosRepository;

@Controller
public class HomeController 
{
	@Autowired
	private ProdutosRepository produtosRepository;
	/*@GetMapping("/inicio")
	public String inicio() {
		return "inicio";
	}*/
	
	@GetMapping("/inicio")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("inicio");

		ArrayList<Produto> TodosProdutos = new ArrayList<Produto>();	
		TodosProdutos.addAll(produtosRepository.findAll());
		
		ArrayList<Produto> ListaProdutosInicio = new ArrayList<Produto>();
		
		
		for(Produto objProd : TodosProdutos) 
		{
			if(objProd.getEstoque() > 0) 
			{
				ListaProdutosInicio.add(objProd);
			}
			
		}
		
		modelAndView.addObject("produtos", ListaProdutosInicio);

		return modelAndView;
	}
	
}

