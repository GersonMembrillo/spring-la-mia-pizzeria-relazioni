package org.java.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaServ {

	@Autowired
	private OffertaRepo offertaRepo; 

	public List<Offerta> findAll() {
		return offertaRepo.findAll();
	}

	public Offerta findById(int id) {
		return offertaRepo.findById(id).get();
	}

	public void save(Offerta Offerta) {
		offertaRepo.save(Offerta);
	}

	public void delete(Offerta Offerta) {
		offertaRepo.delete(Offerta);
	}
	
	public Offerta findByPizza(Pizza pizza) {

	    return offertaRepo.findByPizza(pizza);
	}
}
