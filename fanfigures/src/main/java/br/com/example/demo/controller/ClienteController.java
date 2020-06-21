package br.com.example.demo.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.example.demo.Models.Carrinho;
import br.com.example.demo.Models.Cliente;
import br.com.example.demo.Models.Grupo;
import br.com.example.demo.Models.UsuarioSistema;
import br.com.example.demo.Repositories.ClienteRepository;
import br.com.example.demo.Repositories.EnderecoRepository;
import br.com.example.demo.Repositories.GrupoRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private GrupoRepository grupos;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping("/Admin/listar")
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("cliente/ListarCliente");

		modelAndView.addObject("cliente", clienteRepository.findAll());

		return modelAndView;
	}
	
	@GetMapping("/Admin/novo")
	public ModelAndView novo(Cliente cliente) {       

		ModelAndView modelAndView = new ModelAndView("cliente/CadastroCliente");
		
		modelAndView.addObject("grupos", grupos.findAll());

		modelAndView.addObject(cliente);

		return modelAndView;
	} 
	
	@PostMapping("/Admin/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes)
	{

		if(clienteRepository.existsByEmail(cliente.getEmail()) && !clienteRepository.existsByCodigo(cliente.getCodigo())) 
		{ 
			result.rejectValue("email", "cliente.email.existente");
		}
				
		if (result.hasErrors()) 
			return novo(cliente);
		
		cliente.setAtivo(true);
		String senha = passwordEncoder.encode(cliente.getSenha());
		cliente.setSenha(senha);
		
		clienteRepository.save(cliente);

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!!");

		return new ModelAndView("redirect:/cliente/Admin/novo");
	}
	
	@GetMapping("/Admin/editar/{id}")
	public ModelAndView editar(@PathVariable int id) {

		return novo(clienteRepository.getOne(id));
	}

	@PostMapping(value = "/Admin/excluir/{id}")
	public String excluir(@PathVariable int id, RedirectAttributes attributes) {

		clienteRepository.deleteById(id);

		attributes.addFlashAttribute("mensagem", "Cliente excluido com sucesso!!");

		return "redirect:/cliente/Admin/listar";
	}
	
	@GetMapping("/novo")
	public ModelAndView ClienteNovo(Cliente cliente) {       

		ModelAndView modelAndView = new ModelAndView("cliente/ClienteCad");
		modelAndView.addObject(cliente);

		return modelAndView;
	} 
	 
	@PostMapping("/salvar")
	public ModelAndView ClenteSalvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes)    
	{
		if(clienteRepository.existsByEmail(cliente.getEmail()) && !clienteRepository.existsByCodigo(cliente.getCodigo())) 
		{ 
			result.rejectValue("email", "cliente.email.existente");
		}
				
		if (result.hasErrors()) 
			return ClienteNovo(cliente);
		
		cliente.setAtivo(true);
		String senha = passwordEncoder.encode(cliente.getSenha());
		cliente.setSenha(senha);
		
		Grupo grp = new Grupo();
		long id = 3;
		grp = grupos.getOne(id);
		
		ArrayList<Grupo> GrupoLista = new ArrayList<>();
		GrupoLista.add(grp);
		
		cliente.setGrupos(GrupoLista);
		clienteRepository.save(cliente);

		return new ModelAndView("redirect:/login");
	}
	
	@GetMapping("/MeusDados")
	public ModelAndView ClienteDados(Cliente cliente)    
	{
		ModelAndView modelAndView = new ModelAndView("cliente/MeusDados");
		
		UsuarioSistema xx = ((UsuarioSistema) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		clienteRepository.getOne(xx.getUsuario().getCodigo());
		
		modelAndView.addObject("cliente",clienteRepository.getOne(xx.getUsuario().getCodigo()));
		modelAndView.addObject("endereco", enderecoRepository.findByIdUsuario(xx.getUsuario().getCodigo()));
		return modelAndView;
	}
	
	@PostMapping("/salvarEdit")
	public ModelAndView ClienteSalvarEdit(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes)    
	{
		cliente.setAtivo(true);
		String senha = passwordEncoder.encode(cliente.getSenha());
		cliente.setSenha(senha);
		
		Grupo grp = new Grupo();
		long id = 3;
		grp = grupos.getOne(id);
		
		ArrayList<Grupo> GrupoLista = new ArrayList<>();
		GrupoLista.add(grp);
		
		cliente.setGrupos(GrupoLista);
		clienteRepository.save(cliente);

		return new ModelAndView("redirect:/inicio");
	}
	
}
