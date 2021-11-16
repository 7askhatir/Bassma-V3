package org.basma.store.services.Impl;

import java.lang.reflect.Type;
import java.util.List;

import org.basma.store.entities.CategorieEntity;
import org.basma.store.repositories.CategorieRepository;
import org.basma.store.services.CategorieService;
import org.basma.store.shared.Utils;
import org.basma.store.shared.dto.CategorieDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	CategorieRepository categorieRepository;

	@Autowired
	Utils util;

	@Override
	public CategorieDto createCategorie(CategorieDto categorie) {

		CategorieEntity categorieEntity = new CategorieEntity();

		CategorieEntity checkCategorie = categorieRepository.findByTitreCategorie(categorie.getTitreCategorie());

		if (checkCategorie != null)
			throw new RuntimeException("Categorie Alrady Exists !");

		BeanUtils.copyProperties(categorie, categorieEntity);
		
		categorieEntity.setCategorieId(util.generateStringId(32));

		CategorieEntity newCategorie = categorieRepository.save(categorieEntity);
		
		CategorieDto categorieDto = new CategorieDto();
		
		BeanUtils.copyProperties(newCategorie, categorieDto);

		return categorieDto;
	}

	@Override
	public CategorieDto getCategorie(String titreCategorie) {

		CategorieEntity categorieEntity = categorieRepository.findByTitreCategorie(titreCategorie);

		if (categorieEntity == null)
			throw new UsernameNotFoundException(titreCategorie);

		CategorieDto categorieDto = new CategorieDto();

		BeanUtils.copyProperties(categorieEntity, categorieDto);

		return categorieDto;
	}

	@Override
	public CategorieDto getCategorieByCategorieId(String categorieId) {

		CategorieEntity categorieEntity = categorieRepository.findByCategorieId(categorieId);

		if (categorieEntity == null)
			throw new UsernameNotFoundException(categorieId);

		CategorieDto categorieDto = new CategorieDto();

		BeanUtils.copyProperties(categorieEntity, categorieDto);

		return categorieDto;
	}

	@Override
	public CategorieDto updateCategorie(String id, CategorieDto categorieDto) {
		
		
		CategorieEntity categorieEntity = categorieRepository.findByCategorieId(id);
		
		if (categorieEntity == null)
			throw new UsernameNotFoundException(id);
		
		categorieEntity.setTitreCategorie(categorieDto.getTitreCategorie());
		
		CategorieEntity categorieUpdate =  categorieRepository.save(categorieEntity);
		
		CategorieDto categorie = new CategorieDto();

		BeanUtils.copyProperties(categorieUpdate, categorie);

		return categorie;
	}

	@Override
	public void deleteCategorie(String idCategorie) {

		CategorieEntity categorieEntity = categorieRepository.findByCategorieId(idCategorie);
		
		if (categorieEntity == null)
			throw new UsernameNotFoundException(idCategorie);
		
		categorieRepository.delete(categorieEntity);
		
	}

	@Override
	public List<CategorieDto> getAllCategories() {
		List<CategorieEntity> categories = (List<CategorieEntity>)categorieRepository.findAll();
		Type listType = new TypeToken<List<CategorieDto>>() {}.getType();
		List<CategorieDto> categorieDto = new ModelMapper().map(categories, listType);
		return categorieDto;
	}
	

}
