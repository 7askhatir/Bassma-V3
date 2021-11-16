package org.basma.store.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="categorie")
public class CategorieEntity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8377885370085765517L;

	@Id
	@GeneratedValue
	private int idCategorie;

	
	@Column(nullable=false)
	private String categorieId;
	
	@Column(nullable=false, length=50)
	private String titreCategorie;

	 @OneToMany(mappedBy="categorie", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
		private List<ProductEntity> produits;
	 
	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getTitreCategorie() {
		return titreCategorie;
	}

	public void setTitreCategorie(String titreCategorie) {
		this.titreCategorie = titreCategorie;
	}

	public List<ProductEntity> getProduits() {
		return produits;
	}

	public void setProduits(List<ProductEntity> produits) {
		this.produits = produits;
	}

	public CategorieEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategorieEntity(String titreCategorie, List<ProductEntity> produits) {
		super();
		this.titreCategorie = titreCategorie;
		this.produits = produits;
	}

	public CategorieEntity(String titreCategorie) {
		super();
		this.titreCategorie = titreCategorie;
	}

	public String getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(String categorieId) {
		this.categorieId = categorieId;
	}

	public CategorieEntity(int idCategorie, String categorieId, String titreCategorie, List<ProductEntity> produits) {
		super();
		this.idCategorie = idCategorie;
		this.categorieId = categorieId;
		this.titreCategorie = titreCategorie;
		this.produits = produits;
	}
	 
	 
}
