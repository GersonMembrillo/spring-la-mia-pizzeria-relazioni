package org.java.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PizzaServ pizzaServ;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
  		Pizza pizza1 = new Pizza("Margherita",
  								 "Un'esplosione di sapori e consistenze, con il pomodoro dolce e acidulo, la mozzarella cremosa e filante e il basilico fresco e aromatico. Un piatto semplice ma perfetto, adatto a tutti i palati.",
  								 "https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg", 10.00);
  		Pizza pizza2 = new Pizza("4 Stagioni",
  								 "Un classico intramontabile, con pomodoro, mozzarella, funghi, prosciutto cotto, carciofi e olive. Un mix di sapori e consistenze che conquisterà tutti.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_4_stagioni_la_ricetta_fatta_in_casa_.jpg?itok=EgsCQLDd", 12.00);
  		Pizza pizza3 = new Pizza("Diavola",
  								 "Un'esplosione di sapore per gli amanti del piccante. La base di pomodoro e mozzarella è arricchita con salame piccante",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_rustica.jpg?itok=Lbp_jtZW", 14.00);
  		Pizza pizza4 = new Pizza("Speak & Scamorza",
  								 "Una sinfonia di sapori e consistenze, un impasto fragrante e croccante, un sugo di pomodoro fresco e acidulo, una mozzarella cremosa e filante, una scamorza affumicata decisa e saporita.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza.jpg?itok=z_-RLtOK", 16.00);
  		Pizza pizza5 = new Pizza("Patate & Salsiccia",
  								 "Una pizza invernale, con pomodoro, mozzarella, patate e salsiccia. Un piatto sostanzioso e perfetto per la stagione fredda.",
  								 "https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_con_patate_e_salsiccia.jpg?itok=mxab5rGt", 18.00);
  		
  		pizzaServ.save(pizza1);
  		pizzaServ.save(pizza2);
  		pizzaServ.save(pizza3);
  		pizzaServ.save(pizza4);
  		pizzaServ.save(pizza5);
  		
  		System.out.println("Insert OK");
	}
}