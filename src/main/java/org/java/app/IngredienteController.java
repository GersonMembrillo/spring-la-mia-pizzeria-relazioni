package org.java.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private IngredienteServ ingredienteServ;
	
	@GetMapping
	public String getIndex(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteServ.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingrediente-index";
	}
	
	@GetMapping("/create")
	public String getCreate(Model model) {
		
		List<Pizza> pizze = pizzaServ.findAll();
		Ingrediente ingrediente = new Ingrediente();
		
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("pizze", pizze);
		
		return "ingrediente-create";
	}
	
	@PostMapping("/create")
	public String storeIngrediente(
			@Valid @ModelAttribute Ingrediente ingrediente,
			BindingResult bindingResult,
			Model model
		) {
		
		System.out.println("New ingrediente:\n" + ingrediente);
		System.out.println("Ingrediente pizze:\n" + ingrediente.getPizze());
		
		ingredienteServ.save(ingrediente);
		
//		// RELATION MANAGEMENT: CREATE ONLY
//		for (Pizza pizza : ungrediente.getPizze()) {
//			
//			pizza.addIngrediente(ingrediente);
//			pizzaServ.save(pizza);
//		}
		
//		// RELATION MANAGEMENT: CREATE & UPDATE
		List<Pizza> pizze = pizzaServ.findAll();
		for (Pizza pizza : pizze) {
			
			if (ingrediente.hasPizza(pizza)) 
				pizza.addIngrediente(ingrediente);
			else pizza.removeIngrediente(ingrediente);
			
			pizzaServ.save(pizza);
		}
		
		
		return "redirect:/ingredienti";
	}
}
