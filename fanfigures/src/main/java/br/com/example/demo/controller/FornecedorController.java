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

import br.com.example.demo.Models.Fornecedor;
import br.com.example.demo.Repositories.FornecedorRepository; 

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@GetMapping("/Admin/listar")
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("fornecedor/ListarFornecedor");

		modelAndView.addObject("fornecedor", fornecedorRepository.findAll());

		return modelAndView;
	}
	
	@GetMapping("/Admin/novo")
	public ModelAndView novo(Fornecedor fornecedor) {

		ModelAndView modelAndView = new ModelAndView("fornecedor/CadastroFornecedor");

		modelAndView.addObject(fornecedor);

		return modelAndView;
	} 
	
	@PostMapping("Admin/salvar")
	public ModelAndView salvar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes)
	{

		if (result.hasErrors()) 
			return novo(fornecedor);
		
		fornecedorRepository.save(fornecedor);

		attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!!");

		return new ModelAndView("redirect:/fornecedor/Admin/novo");
	}
	
	@GetMapping("/Admin/editar/{id}")
	public ModelAndView editar(@PathVariable int id) {

		return novo(fornecedorRepository.getOne(id));
	}

	@PostMapping(value = "/Admin/excluir/{id}")
	public String excluir(@PathVariable int id, RedirectAttributes attributes) {

		fornecedorRepository.deleteById(id);

		attributes.addFlashAttribute("mensagem", "Fornecedor excluido com sucesso!!");

		return "redirect:/fornecedor/Admin/listar";
	}
}
