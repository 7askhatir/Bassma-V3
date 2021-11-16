package org.basma.store.test;

import java.util.ArrayList;
import java.util.List;

import org.basma.store.services.ProductService;
import org.basma.store.shared.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {

	@Autowired
	ProductService productService;

	@Test
	public void addProduct() {

		ArrayList<String> images = new ArrayList<String>();

		
		
		ProductDto productDto = new ProductDto("ssss", "hisi", 11.3, 11, images);
		images.add("One");
		images.add("Two");
		images.add("Three");
		images.add("Four");
		productService.createProduct(2, productDto);
	}

//	@Test
//	public void getProduct() {
//		ProductDto productDto = productService.getProduct("hixxi");
//		System.out.println("////// "+productDto.getTitleProduct()+" //////");
//	}

//	@Test
//	public void updateProduct() {
//		ProductDto prodDto = new ProductDto();
//		ProductDto productDto = productService.updateProduct("KfTfLyQMAdjVrH01AUoWlBZojIhIgtWo", prodDto);
//		productDto.setTitleProduct("rrrrr");
//		System.out.println("////// " + productDto.getTitleProduct() + " //////");
//	}	
	
//	@Test
//	public void getAllProduct() {
//		ArrayList<ProductDto> productDto = (ArrayList<ProductDto>) productService.getAllProducts();
//		System.out.println("////// "+productDto.toString()+" //////");
//	}
}
