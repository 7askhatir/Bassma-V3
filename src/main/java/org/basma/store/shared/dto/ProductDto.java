package org.basma.store.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7847950237834629532L;

	private long id;
	
	private String idProduct;
	
	private String titleProduct;
	
	private String descriptionProduct;
	
	private double prixProduct;
	
	private int qtStockProduct;
	
	ArrayList<String> listImages = new ArrayList<String>();
	
	private CategorieDto categorie;

	
	
	
	public CategorieDto getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieDto categorie) {
		this.categorie = categorie;
	}

	public ProductDto(long id, String idProduct, String titleProduct, String descriptionProduct, double prixProduct,
			int qtStockProduct, ArrayList<String> listImages, CategorieDto categorie) {
		super();
		this.id = id;
		this.idProduct = idProduct;
		this.titleProduct = titleProduct;
		this.descriptionProduct = descriptionProduct;
		this.prixProduct = prixProduct;
		this.qtStockProduct = qtStockProduct;
		this.listImages = listImages;
		this.categorie = categorie;
	}

	public ProductDto(String titleProduct, String descriptionProduct, double prixProduct, int qtStockProduct,
			ArrayList<String> listImages) {
		super();
		this.titleProduct = titleProduct;
		this.descriptionProduct = descriptionProduct;
		this.prixProduct = prixProduct;
		this.qtStockProduct = qtStockProduct;
		this.listImages = listImages;
	}

	public ProductDto(String titleProduct, String descriptionProduct, double prixProduct, int qtStockProduct,
			ArrayList<String> listImages, int idCategorie) {
		super();
		this.titleProduct = titleProduct;
		this.descriptionProduct = descriptionProduct;
		this.prixProduct = prixProduct;
		this.qtStockProduct = qtStockProduct;
		this.listImages = listImages;
		//this.idCategorie = idCategorie;
	}

//	public int getIdCategorie() {
//		return idCategorie;
//	}
//
//	public void setIdCategorie(int idCategorie) {
//		this.idCategorie = idCategorie;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public String getTitleProduct() {
		return titleProduct;
	}

	public void setTitleProduct(String titleProduct) {
		this.titleProduct = titleProduct;
	}

	public String getDescriptionProduct() {
		return descriptionProduct;
	}

	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}

	public double getPrixProduct() {
		return prixProduct;
	}

	public void setPrixProduct(double prixProduct) {
		this.prixProduct = prixProduct;
	}

	public int getQtStockProduct() {
		return qtStockProduct;
	}

	public void setQtStockProduct(int qtStockProduct) {
		this.qtStockProduct = qtStockProduct;
	}

	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String> getListImages() {
		return listImages;
	}

	public void setListImages(ArrayList<String> listImages) {
		this.listImages = listImages;
	}
}