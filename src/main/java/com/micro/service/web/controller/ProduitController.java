package com.micro.service.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.micro.service.dao.ProductDao;
import com.micro.service.exceptions.ProduitIntrouvable;
import com.micro.service.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@Api( description=" il s'agit du microservice qui gère toutes les methodes des produits")
@RestController
public class ProduitController {
	
	@Autowired 
	private ProductDao productdao; 
	
     @ApiOperation(value="il s'agit de la methode qui se charge de génerer la liste de tous le sproduits du microservce")
	@GetMapping("/Produits")
	public List<Product> listeProduits()
	{
		return productdao.findAll();
	}  
	
   	@GetMapping("/Produits/{id}")
	public 	Product afficherUnProduit(@Valid @PathVariable int id) throws ProduitIntrouvable 
	{
	
		Product produit =  productdao.findById(id); 
		if(produit == null)
		{
			throw new ProduitIntrouvable("le produit avec lidentifiant "+ id +" n'existe pas");
		}
		
		return produit ;
	}    
   	
	@GetMapping("/test/Produits/{prixLimit}")
	public 	List<Product> testeDeRequete(@PathVariable int prixLimit)
	{
	
		return productdao.findByPrixGreaterThan(prixLimit);
	}    
	
	
   	
	@PostMapping(value ="/Produits")
	public ResponseEntity<Void> ajouterProduct(@RequestBody Product product)
	{
		  Product productAdded =  productdao.save(product);

	        if (productAdded == null)
	            return ResponseEntity.noContent().build();

	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(productAdded.getId())
	                .toUri();

	        return ResponseEntity.created(location).build();
		
	}
	 
	@DeleteMapping("/Produits/{id}")
	 public void deleteProduit(@PathVariable int id)
	 {
		 productdao.deleteById(id);
	 }
	
	@PutMapping("/Produits")
	public Product mettreAJour(@RequestBody Product product)
	{
		productdao.save(product);
		return product;
	}
	     

}
