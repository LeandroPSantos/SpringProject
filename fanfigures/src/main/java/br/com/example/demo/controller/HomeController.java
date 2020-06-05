package br.com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController 
{
	/*@RequestMapping(method=RequestMethod.GET, value = "/inicio")
	public ModelAndView home() 
	{
		ModelAndView mv = new ModelAndView("Home");
		return mv;
	}*/
	@GetMapping("/inicio")
	public String inicio() {
		return "inicio";
	}
	
}
