package org.java.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private OffertaServ offertaServ;

	@GetMapping
	public String getIndex(@RequestParam(required = false, name = "search") String searchTitle, Model model) {

		System.out.println("search: " + searchTitle);

//		List<Pizza> pizze = pizzaServ.findAll();
		List<Pizza> pizze = searchTitle == null ? pizzaServ.findAll() : pizzaServ.findByName(searchTitle);

		model.addAttribute("pizze", pizze);
		model.addAttribute("searchTitle", searchTitle);

		return "pizza-index";
	}

	@GetMapping("/{id}")
	public String getShow(@PathVariable Integer id, Model model) {
		Pizza pizza = pizzaServ.findById(id);
		model.addAttribute("pizza", pizza);
		return "pizza-show";
	}

	@GetMapping("/create")
	public String getCreateForm(Model model) {

		model.addAttribute("pizza", new Pizza());

		return "pizza-create";
	}

	@PostMapping("/create")
	public String storeNewPizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {

		return storePizza(pizza, bindingResult);
	}

	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable int id, Model model) {

		Pizza pizza = pizzaServ.findById(id);
		model.addAttribute("pizza", pizza);

		return "pizza-create";
	}

	@PostMapping("/edit/{id}")
	public String updatePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {

		return storePizza(pizza, bindingResult);
	}

	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id) {

		Pizza pizza = pizzaServ.findById(id);
		pizzaServ.deletePizza(pizza);

		return "redirect:/";
	}

	private String storePizza(Pizza pizza, BindingResult bindingResult) {

		System.out.println("pizza:\n" + pizza);

		if (bindingResult.hasErrors()) {

			System.out.println("Errors");
			bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).forEach(System.out::println);

			return "pizza-form";
		}

		pizzaServ.save(pizza);

		return "redirect:/";
	}
	
	@GetMapping("/offerta/create")
	public String getOffertaForm(Model model) {
		List<Pizza> pizze = pizzaServ.findAll();
		model.addAttribute("pizze", pizze);
		model.addAttribute("Offerta", new Offerta());
		return "offerta-create";
	}

	@PostMapping("/offerta/create")
	public String createOfferta(@Valid @ModelAttribute Offerta Offerta,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "offerta-create";
		}
		int pizzaId = Offerta.getPizza().getId();
		Pizza pizza = pizzaServ.findById(pizzaId);
		Offerta.setPizza(pizza);
		offertaServ.save(Offerta);

		return "redirect:/" + pizzaId;
	}

	@GetMapping("/offerta/edit/{offertaId}")
	public String getOffertaUpdate(@PathVariable int offertaId, Model model) {
		Offerta Offerta = offertaServ.findById(offertaId);

		model.addAttribute("Offerta", Offerta);

		return "offerta-edit";
	}

	@PostMapping("/offerta/edit/{offertaId}")
	public String updateOfferta(@PathVariable int offertaId,
			@Valid @ModelAttribute Offerta Offerta, @RequestParam("pizzaId") int pizzaId,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "offerta-edit";
		}

		Pizza pizza = pizzaServ.findById(pizzaId);
		Offerta.setPizza(pizza);

		Offerta.setId(offertaId);
		offertaServ.save(Offerta);

		return "redirect:/" + pizzaId;
	}

}

