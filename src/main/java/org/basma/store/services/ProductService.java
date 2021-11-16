package org.basma.store.services;

import java.util.List;
 
import org.basma.store.shared.dto.ProductDto; 

public interface ProductService {
	
	ProductDto createProduct(int categorie,ProductDto product);

	ProductDto getProduct(String titleProduct);

	ProductDto getProductByProductId(String idProduct);

	ProductDto updateProduct(String id, ProductDto productDto);

	void deleteProduct(String idProduct);

	List<ProductDto> getAllProducts();
	
	
 
}
