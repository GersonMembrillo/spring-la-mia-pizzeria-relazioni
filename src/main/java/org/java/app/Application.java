package org.java.app;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private OffertaServ offertaServ;
	
	@Autowired
	private IngredienteServ ingredienteServ;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingrediente ingrediente1 = new Ingrediente("Farina", "di grano tenero");
		Ingrediente ingrediente2 = new Ingrediente("Pomodoro", "San Marzano");
		Ingrediente ingrediente3 = new Ingrediente("Mozzarella", "formaggio");
		
		ingredienteServ.save(ingrediente1);
		ingredienteServ.save(ingrediente2);
		ingredienteServ.save(ingrediente3);
		
  		Pizza pizza1 = new Pizza("Margherita",
  								 "Un'esplosione di sapori e consistenze, con il pomodoro dolce e acidulo, la mozzarella cremosa e filante e il basilico fresco e aromatico. Un piatto semplice ma perfetto, adatto a tutti i palati.",
  								 "https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg", 10.00, ingrediente1, ingrediente2, ingrediente3);
  		Pizza pizza2 = new Pizza("4 Stagioni",
  								 "Un classico intramontabile, con pomodoro, mozzarella, funghi, prosciutto cotto, carciofi e olive. Un mix di sapori e consistenze che conquisterà tutti.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_4_stagioni_la_ricetta_fatta_in_casa_.jpg?itok=EgsCQLDd", 12.00, ingrediente1, ingrediente2, ingrediente3);
  		Pizza pizza3 = new Pizza("Diavola",
  								 "Un'esplosione di sapore per gli amanti del piccante. La base di pomodoro e mozzarella è arricchita con salame piccante",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_rustica.jpg?itok=Lbp_jtZW", 14.00, ingrediente1, ingrediente2, ingrediente3);
  		Pizza pizza4 = new Pizza("Speak & Scamorza",
  								 "Una sinfonia di sapori e consistenze, un impasto fragrante e croccante, un sugo di pomodoro fresco e acidulo, una mozzarella cremosa e filante, una scamorza affumicata decisa e saporita.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza.jpg?itok=z_-RLtOK", 16.00, ingrediente1, ingrediente3);
  		Pizza pizza5 = new Pizza("Patate & Salsiccia",
  								 "Una pizza invernale, con pomodoro, mozzarella, patate e salsiccia. Un piatto sostanzioso e perfetto per la stagione fredda.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_con_patate_e_salsiccia.jpg?itok=mxab5rGt", 18.00, ingrediente1, ingrediente3);
  		
  		pizzaServ.save(pizza1);
  		pizzaServ.save(pizza2);
  		pizzaServ.save(pizza3);
  		pizzaServ.save(pizza4);
  		pizzaServ.save(pizza5);
  		
  		System.out.println("Insert OK");
  		
  		Offerta offerta1 = new Offerta("Due pizze al prezzo di una!", LocalDate.now(), 
				LocalDate.parse("2023-11-20"), pizza1);
		Offerta offerta2 = new Offerta("Paghi 3, prendi 2!", LocalDate.now(), 
				LocalDate.parse("2023-12-25"), pizza2);
		Offerta offerta3 = new Offerta("1 bibita gratis con l'acquisto di 2 pizze!", LocalDate.now(), 
				LocalDate.parse("2023-12-31"), pizza4);
		
		offertaServ.save(offerta1);
		offertaServ.save(offerta2);
		offertaServ.save(offerta3);
	}
}