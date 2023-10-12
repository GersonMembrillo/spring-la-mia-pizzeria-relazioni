package org.java.app;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 64)
    @Length(min = 3, max = 64, message = "Il nome deve essere compreso tra 3 e 64 caratteri")
    private String name;

    @Length(max = 255)
    private String description;

    @Length(max = 255)
    private String photo;

    @Min(0)
    @Max(100)
    private double price;
    
    @OneToMany(mappedBy= "pizza")
	  private List<Offerta> offerta;

    public Pizza() { }

    public Pizza(String name, String description, String photo, double price) {
        setName(name);
        setDescription(description);
        setPhoto(photo);
        setPrice(price);
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

 // Aggiunto metodo per i decimali dopo la virgola, credo serva per gli sconti in %
    public String getFirstTwoDecimalDigits() {
        // prezz in stringa con 2 decimali
        String priceStr = String.format("%.2f", price);

        // ricerca indice . decimale
        int indexOfDecimalPoint = priceStr.indexOf(".");

        // ci sono 2 cifre decimali e un . decimale?
        if (indexOfDecimalPoint != -1 && indexOfDecimalPoint + 3 < priceStr.length()) {
            // estraili
            return priceStr.substring(indexOfDecimalPoint + 1, indexOfDecimalPoint + 3);
        }
        // se no, restituisce una stringa vuota
        return "";
    }

    @Override
    public String toString() {
        return "[" + getId() + "] " + getName() + ": " + getPrice() + "\n" + getDescription() + "\n" + getPhoto();
    }
}
