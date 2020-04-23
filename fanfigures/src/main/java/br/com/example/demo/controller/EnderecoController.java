package br.com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.example.demo.Models.Endereco;
import br.com.example.demo.Repositories.EnderecoRepository;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Endereco endereco) {

		ModelAndView modelAndView = new ModelAndView("cliente/CadastroEndereco");

		modelAndView.addObject(endereco);

		return modelAndView;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Endereco endereco, BindingResult result, RedirectAttributes attributes)
	{

		if (result.hasErrors()) 
			return novo(endereco);
		
		enderecoRepository.save(endereco);

		attributes.addFlashAttribute("mensagem", "Endereço salvo com sucesso!!");

		return new ModelAndView("redirect:/endereco/novo");
	}
}
