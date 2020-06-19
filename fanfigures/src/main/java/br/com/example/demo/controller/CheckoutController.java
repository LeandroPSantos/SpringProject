package br.com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.example.demo.Models.Carrinho;
import br.com.example.demo.Models.Generico;

@Controller
@RequestMapping("/Checkout") 
public class CheckoutController {

	@GetMapping
	public ModelAndView checkout() {

		ModelAndView modelAndView = new ModelAndView("carrinhoCompra/Checkout");
		return modelAndView;
		
	}
}
