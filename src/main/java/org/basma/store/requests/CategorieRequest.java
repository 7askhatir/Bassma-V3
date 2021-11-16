package org.basma.store.requests;

import java.util.List;

public class CategorieRequest {
	
	
	private String titreCategorie;
	private List<ProductRequest> products;

	
	public List<ProductRequest> getProducts() {
		return products;
	}

	public void setProducts(List<ProductRequest> products) {
		this.products = products;
	}

	public String getTitreCategorie() {
		return titreCategorie;
	}

	public void setTitreCategorie(String titreCategorie) {
		this.titreCategorie = titreCategorie;
	}
	
}
