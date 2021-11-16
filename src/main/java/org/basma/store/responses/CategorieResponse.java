package org.basma.store.responses;

import java.util.List;

public class CategorieResponse {

	private String titreCategorie;


	private List<ProductResponse> products;
	
	
	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

	public String getTitreCategorie() {
		return titreCategorie;
	}

	public void setTitreCategorie(String titreCategorie) {
		this.titreCategorie = titreCategorie;
	}
	
	
}
