package com.micro.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Length(min=3, max=20, message="la longeur du nom ne doit pas dépasser 20")
	private String nom;
	private int prix;
	private int prixAchat ;
	
	public Product()
	{
		
	}
	
	

	public Product(int id, String nom, int prix) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	

	 public int getPrixAchat() {
		return prixAchat;
	}



	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}



	@Override
	    public String toString(){  
	        return "Product{"+
	        "id=" + id + 
	        ", nom='"+ nom + '\'' + 
	        ", prix=" + prix+ '}';
	    }
	
	

}
