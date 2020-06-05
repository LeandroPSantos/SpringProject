package br.com.example.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.example.demo.Models.Produto;
import br.com.example.demo.Repositories.ProdutosRepository;
import br.com.example.demo.infra.fotoService;

@Controller
@RequestMapping("/produto") //com isso todos os métodos dessa controller começam a partir de /produto
public class ProdutoController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@Autowired
	private fotoService foto_Service;
	
	@GetMapping("/Admin/listar")
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("produto/listaProdutos");

		modelAndView.addObject("produtos", produtosRepository.findAll());

		return modelAndView;
	}
	
	@GetMapping("/Admin/novo")
	public ModelAndView novo(Produto produto) {

		ModelAndView modelAndView = new ModelAndView("produto/CadastroProduto");

		modelAndView.addObject(produto);

		return modelAndView;
	}
	
	@PostMapping("/Admin/salvar")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file, @Valid Produto produto, BindingResult result, RedirectAttributes attributes)
	{

		if (result.hasErrors()) 
			return novo(produto);
		
		if (!file.isEmpty()) {
			
			if(produto.getUrl_imagem() != null && !produto.getUrl_imagem().isEmpty()) {
				foto_Service.removerFoto(produto.getUrl_imagem());
			}
			
			String arquivoFoto = foto_Service.doUpload(file, produto);

			if (arquivoFoto.equals("erro")) {
				attributes.addFlashAttribute("mensagem", "Problemas para salvar a imagem do produto!!");
				return novo(produto);
			} else {
				produto.setUrl_imagem(arquivoFoto);
			}
		}

		produtosRepository.save(produto);

		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!!");

		return new ModelAndView("redirect:/produto/Admin/novo");

	}
	
	@GetMapping("/Admin/editar/{id}")
	public ModelAndView editar(@PathVariable int id) {

		return novo(produtosRepository.getOne(id));
	}

	@PostMapping(value = "/Admin/excluir/{id}")
	public String excluir(@PathVariable int id, RedirectAttributes attributes) {

		produtosRepository.deleteById(id);

		attributes.addFlashAttribute("mensagem", "Produto excluido com sucesso!!");
 
		return "redirect:/produto/Admin/listar";
	}
	
	@GetMapping("/imagem/{nome:.*}")
	public @ResponseBody byte[] recuperarFoto(@PathVariable String nome) throws IOException {
		
		return foto_Service.recuperarFoto(nome);
	}
}
