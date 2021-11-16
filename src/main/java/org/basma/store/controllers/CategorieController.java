package org.basma.store.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.basma.store.requests.CategorieRequest;
import org.basma.store.responses.CategorieResponse ;
import org.basma.store.services.CategorieService;
import org.basma.store.shared.dto.CategorieDto; 
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/categories") // localhost:8080/categories

public class CategorieController {

	@Autowired
	CategorieService categorieService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<CategorieResponse> getCategorie(@PathVariable String id) {

		CategorieDto categorieDto = categorieService.getCategorieByCategorieId(id);

		CategorieResponse categorieResponse = new CategorieResponse();

		BeanUtils.copyProperties(categorieDto, categorieResponse);

		return new ResponseEntity<CategorieResponse>(categorieResponse, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<CategorieResponse>>getCategorie(){
		List<CategorieDto> categories = categorieService.getAllCategories();
		Type listType = new TypeToken <List<CategorieResponse>>() {}.getType();
		List<CategorieResponse> categoriesResponse = new ModelMapper().map(categories, listType);
		return new ResponseEntity<List<CategorieResponse>>(categoriesResponse, HttpStatus.OK);

	}
	
	
	@PostMapping
	public ResponseEntity<CategorieResponse> createCategorie(@RequestBody CategorieRequest categorieRequest)
			throws Exception {

		CategorieDto categorieDto = new CategorieDto();
		BeanUtils.copyProperties(categorieRequest, categorieDto);

		CategorieDto createCategorie = categorieService.createCategorie(categorieDto);
		CategorieResponse categorieResponse = new CategorieResponse();

		BeanUtils.copyProperties(createCategorie, categorieResponse);

		return new ResponseEntity<CategorieResponse>(categorieResponse, HttpStatus.OK);

	}
	

	@PutMapping(path = "/{id}")
	public ResponseEntity<CategorieResponse> updateCategorie(@PathVariable String id,
			@RequestBody CategorieRequest categorieRequest) {

		CategorieDto categorieDto = new CategorieDto();
		BeanUtils.copyProperties(categorieRequest, categorieDto);

		CategorieDto udpateCategorie = categorieService.updateCategorie(id, categorieDto);

		CategorieResponse categorieResponse = new CategorieResponse();
		BeanUtils.copyProperties(udpateCategorie, categorieResponse);

		return new ResponseEntity<CategorieResponse>(categorieResponse, HttpStatus.OK);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteCategorie(@PathVariable String id) {
		categorieService.deleteCategorie(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}
