package br.com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

		modelAndView.addObject("produtos", produtosRepository.findAll());

		return modelAndView;
	}
	
}

