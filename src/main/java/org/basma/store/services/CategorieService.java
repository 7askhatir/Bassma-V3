package org.basma.store.services;

import java.util.List;

import org.basma.store.shared.dto.CategorieDto;
import org.basma.store.shared.dto.ProductDto; 

public interface CategorieService {

	CategorieDto createCategorie(CategorieDto categorie);

	CategorieDto getCategorie(String titreCategorie);

	CategorieDto getCategorieByCategorieId(String categorieId);

	CategorieDto updateCategorie(String id, CategorieDto categorieDto);

	void deleteCategorie(String categorieId);


	List<CategorieDto> getAllCategories();

	
}