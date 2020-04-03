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

import br.com.example.demo.Models.Cliente;
import br.com.example.demo.Repositories.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {

		ModelAndView modelAndView = new ModelAndView("cliente/CadastroCliente");

		modelAndView.addObject(cliente);

		return modelAndView;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes)
	{

		if (result.hasErrors()) 
			return novo(cliente);
		
		cliente.setTipo_usuario("F");
		clienteRepository.save(cliente);

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!!");

		return new ModelAndView("redirect:/cliente/novo");

	}
	
}
