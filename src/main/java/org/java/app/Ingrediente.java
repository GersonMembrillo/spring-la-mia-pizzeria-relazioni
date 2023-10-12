package org.java.app;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	private String description;
	
	@ManyToMany(mappedBy = "ingredienti")
	private List<Pizza> pizze;
	
	public Ingrediente() { }
	public Ingrediente(String name, String description, Pizza... pizze) {
		
		setName(name);
		setDescription(description);
		setPizze(Arrays.asList(pizze));
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Pizza> getPizze() {
		return pizze;
	}
	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	public boolean hasPizza(Pizza pizza) {
		
		if (getPizze() == null) return false;
		
		for (Pizza p : getPizze()) 
			if (p.getId() == pizza.getId())
				return true;
		
		return false;
	}
	public void addPizze(Pizza... pizze) {
		
		getPizze().addAll(Arrays.asList(pizze));
	}
	public void removePizze(Pizza... pizze) {
		
		getPizze().removeAll(Arrays.asList(pizze));
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getName() + "\n" + getDescription();
	}
}

