package org.basma.store.test;

import org.basma.store.requests.CategorieRequest;
import org.basma.store.services.CategorieService;
import org.basma.store.shared.dto.CategorieDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategorieTest {

	@Autowired
	CategorieService categorieService;

	CategorieRequest categorieRequest;

	@Test
	public void addCategorie() {
		CategorieDto categorieDto = new CategorieDto("Exmple1");
		categorieService.createCategorie(categorieDto);
	}

//	@Test
//	public void getCategorie() {
//		CategorieDto categorieDto = categorieService.getCategorie("Exmple1");
//		System.out.println("////// "+categorieDto.getTitreCategorie()+" //////");
//	}

//	@Test
//	public void updateCategorie() {
//
//		CategorieDto cateDto = new CategorieDto();
//		CategorieDto categorieDto = categorieService.updateCategorie("7pqlgJu6pCdEcMtd3GoCfBdcRK392ZNk", cateDto);
//		categorieDto.setTitreCategorie("ChangeTitre");
//		System.out.println("////// " + categorieDto.getTitreCategorie() + " //////");
//	}
}
