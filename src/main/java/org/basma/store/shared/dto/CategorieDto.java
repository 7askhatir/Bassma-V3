package org.basma.store.shared.dto;

import java.io.Serializable;
import java.util.List;

import org.basma.store.entities.CategorieEntity;

public class CategorieDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624704134195738202L;

	private int id;
	private String categorieId;
	private String titreCategorie;
	private List<ProductDto> products;

	
	
	public String getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(String categorieId) {
		this.categorieId = categorieId;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdCategorie() {
		return categorieId;
	}

	public void setIdCategorie(String idCategorie) {
		this.categorieId = idCategorie;
	}

	public String getTitreCategorie() {
		return titreCategorie;
	}

	public void setTitreCategorie(String titreCategorie) {
		this.titreCategorie = titreCategorie;
	}

	public CategorieDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategorieDto(String titreCategorie) {
		this.titreCategorie = titreCategorie;
	}

	public CategorieDto(int id, String categorieId, String titreCategorie, List<ProductDto> products) {
		super();
		this.id = id;
		this.categorieId = categorieId;
		this.titreCategorie = titreCategorie;
		this.products = products;
	}

	public CategorieDto(int id) {
		super();
		this.id = id;
	}

	public CategorieDto(CategorieEntity categorie) {
		this.id = categorie.getIdCategorie();
		this.categorieId = categorieId;
		this.titreCategorie = titreCategorie;
		this.products = products;
	}

 
}
