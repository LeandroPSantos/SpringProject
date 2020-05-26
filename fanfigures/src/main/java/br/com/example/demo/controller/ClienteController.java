package br.com.example.demo.controller;

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

import br.com.example.demo.Models.Cliente;
import br.com.example.demo.Repositories.ClienteRepository;
import br.com.example.demo.Repositories.GrupoRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private GrupoRepository grupos;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ModelAndView listar() {

		/*ModelAndView modelAndView = new ModelAndView("usuario/listausuarios");

		modelAndView.addObject("usuarios", usuari os.findAll());

		return modelAndView;*/
		return null;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {       

		ModelAndView modelAndView = new ModelAndView("cliente/CadastroCliente");
		
		modelAndView.addObject("grupos", grupos.findAll());

		modelAndView.addObject(cliente);

		return modelAndView;
	} 
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes)
	{

		if(clienteRepository.existsByEmail(cliente.getEmail()) && !clienteRepository.existsByCodigo(cliente.getCodigo())) 
		{ 
			result.rejectValue("email", "cliente.email.existente");
		}
				
		if (result.hasErrors()) 
			return novo(cliente);
		
		cliente.setAtivo(true);
		//String senha = passwordEncoder.encode(cliente.getSenha());
		//cliente.setSenha(senha);
		
		clienteRepository.save(cliente);

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!!");

		return new ModelAndView("redirect:/cliente/novo");
	}
	
	@GetMapping("editar/{id}")
	public ModelAndView editar(@PathVariable int id) {

		return novo(clienteRepository.getOne(id));
	}

	@PostMapping(value = "excluir/{id}")
	public String excluir(@PathVariable int id, RedirectAttributes attributes) {

		clienteRepository.deleteById(id);

		attributes.addFlashAttribute("mensagem", "Cliente excluido com sucesso!!");

		return "redirect:/cliente/novo";
	}
	
}
