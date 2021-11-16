package org.basma.store.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.basma.store.repositories.CategorieRepository;
import org.basma.store.requests.ProductRequest;
import org.basma.store.responses.ProductResponse;
import org.basma.store.services.ProductService;
import org.basma.store.shared.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//chrome.exe --user-data-dir="C://Chrome dev session" --disable-web-security
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/products") // localhost:8080/products
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategorieRepository categorieRepository;

//	@GetMapping
//	public ResponseEntity<List<ProductResponse>> getProducts() {
//		List<ProductDto> products = productService.getAllProducts();
//		Type listType = new TypeToken<List<ProductResponse>>() {}.getType();
//		ModelMapper modelMapper = new ModelMapper();
//		List<ProductResponse> productsResponse = modelMapper.map(products, listType);
//		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
//	}

	
	 
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.valueOf(200));
    }
	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable String id) {

		ProductDto productDto = productService.getProductByProductId(id);

		ProductResponse productResponse = new ProductResponse();

		BeanUtils.copyProperties(productDto, productResponse);

		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) throws Exception {

		ProductDto productDto = new ProductDto();

		BeanUtils.copyProperties(productRequest, productDto);

		ProductDto createProduct = productService.createProduct(productRequest.getIdCategorie(), productDto);
		ProductResponse productResponse = new ProductResponse();

		BeanUtils.copyProperties(createProduct, productResponse);

		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);

	}
}
