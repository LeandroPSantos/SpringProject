package br.com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.example.demo.Models.Carrinho;
import br.com.example.demo.Models.DetalhesVenda;
import br.com.example.demo.Models.Generico;
import br.com.example.demo.Models.MinhasCompras;
import br.com.example.demo.Models.Produto;
import br.com.example.demo.Models.UsuarioSistema;
import br.com.example.demo.Models.Venda;
import br.com.example.demo.Repositories.DetalheVendaRepository;
import br.com.example.demo.Repositories.ProdutosRepository;
import br.com.example.demo.Repositories.VendaRepository;

@Controller
@RequestMapping("/MeusPedidos")
public class MeusPedidosController {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private DetalheVendaRepository detalheVendaRepository;
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("cliente/MeusPedidos");
		
		ArrayList<Venda> listaVendas = new ArrayList<Venda>();
		
		ArrayList<MinhasCompras> listaMinhasCompras = new ArrayList<MinhasCompras>();
		
		UsuarioSistema xx = ((UsuarioSistema) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		listaVendas = vendaRepository.findByidUsuario(xx.getUsuario().getCodigo());
		
		if (!listaVendas.isEmpty()) 
		{
			for (Venda obj : listaVendas) 
			{
				MinhasCompras objMinhaCompras = new MinhasCompras();
				objMinhaCompras.setDatacompra(obj.getDatacompra());
				
				if(obj.getForma_pagamento().toUpperCase() == "BO")
				{
					objMinhaCompras.setForma_pagamento("boleto bancario");
					objMinhaCompras.setNumero_parcelas(1);
				}	
				else if(obj.getForma_pagamento().toUpperCase() == "CC")
				{
					objMinhaCompras.setForma_pagamento("cartão de crédito");
					objMinhaCompras.setNumero_parcelas(obj.getNumero_parcelas());
				}
				else 
				{
					objMinhaCompras.setForma_pagamento("cartão de débito");
					objMinhaCompras.setNumero_parcelas(1);
				}
				
				
				objMinhaCompras.setId_endereco(obj.getId_endereco());
				objMinhaCompras.setNumero_pedido(obj.getNumero_pedido());
				objMinhaCompras.setTotal_compra(obj.getTotal_compra());
				
				ArrayList<DetalhesVenda> listaDetalheVendas = new ArrayList<DetalhesVenda>();
				listaDetalheVendas.addAll(detalheVendaRepository.findByidVenda(obj.getIdvenda()));
				
				ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
				for (DetalhesVenda objDetalhe : listaDetalheVendas) 
				{
					Produto objProduto = new Produto();
					objProduto = produtosRepository.getOne(objDetalhe.getId_produto());
					objProduto.setQuantidade(objDetalhe.getQuantidade());
					
					listaProdutos.add(objProduto);
				}
				
							
				objMinhaCompras.setProdutos(listaProdutos);
				
				listaMinhasCompras.add(objMinhaCompras);
				
				/*for(MinhasCompras obj2 : listaMinhasCompras) 
				{
					for(DetalhesVenda detalhe2 : obj2.getDetalheVendas()) 
					{
						System.out.println(detalhe2.getId_produto());
					}
				}*/
				
			}
			
		}
		
		modelAndView.addObject("listaMinhasCompras", listaMinhasCompras);

		return modelAndView;
	}
}
