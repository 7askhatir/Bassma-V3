package org.basma.store.services.Impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.basma.store.entities.ProductEntity;
import org.basma.store.repositories.CategorieRepository;
import org.basma.store.repositories.ProductRepository;
import org.basma.store.services.ProductService;
import org.basma.store.shared.Utils;
import org.basma.store.shared.dto.CategorieDto;
import org.basma.store.shared.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	Utils util;

	@Autowired
	CategorieRepository categorieRepository;

	@Override
	public ProductDto createProduct(int categorie, ProductDto product) {

		ProductEntity productEntity = new ProductEntity();

		ProductEntity checkProduct = productRepository.findByTitleProduct(product.getTitleProduct());

		if (checkProduct != null)
			throw new RuntimeException("Product Alrady Exists !");

		BeanUtils.copyProperties(product, productEntity);

		productEntity.setCategorie(categorieRepository.findByIdCategorie(categorie));

		productEntity.setProductId(util.generateStringId(32));

		ProductEntity newProduct = productRepository.save(productEntity);

		ProductDto prodyctDto = new ProductDto();

		BeanUtils.copyProperties(newProduct, prodyctDto);

		return prodyctDto;
	}

	@Override
	public ProductDto getProduct(String titleProduct) {

		ProductEntity productEntity = productRepository.findByTitleProduct(titleProduct);

		if (productEntity == null)
			throw new UsernameNotFoundException(titleProduct);

		ProductDto prodyctDto = new ProductDto();

		BeanUtils.copyProperties(productEntity, prodyctDto);

		return prodyctDto;
	}

	@Override
	public ProductDto getProductByProductId(String idProduct) {

		ProductEntity productEntity = productRepository.findByProductId(idProduct);

		if (productEntity == null)
			throw new UsernameNotFoundException(idProduct);

		ProductDto prodyctDto = new ProductDto();

		BeanUtils.copyProperties(productEntity, prodyctDto);

		return prodyctDto;
	}

	@Override
	public ProductDto updateProduct(String id, ProductDto productDto) {

		ProductEntity productEntity = productRepository.findByProductId(id);

		if (productEntity == null)
			throw new UsernameNotFoundException(id);

		productEntity.setTitleProduct(productDto.getTitleProduct());
		productEntity.setDescriptionProduct(productDto.getDescriptionProduct());
		productEntity.setQtStockProduct(productDto.getQtStockProduct());
		productEntity.setPrixProduct(productDto.getPrixProduct());

		ProductEntity productUpdate = productRepository.save(productEntity);

		ProductDto product = new ProductDto();

		BeanUtils.copyProperties(productUpdate, product);

		return product;
	}

	@Override
	public void deleteProduct(String idProduct) {

		ProductEntity productEntity = productRepository.findByProductId(idProduct);

		if (productEntity == null)
			throw new UsernameNotFoundException(idProduct);

		productRepository.delete(productEntity);

	}

	@Override
	public List<ProductDto> getAllProducts() {
        List<ProductDto> productsDto = new ArrayList<>();
        for (ProductEntity produit : productRepository.findAll()) {  
        	
        	productsDto.add(new ProductDto(produit.getIdProduct(),produit.getProductId(),produit.getTitleProduct(),produit.getDescriptionProduct(),produit.getPrixProduct(),produit.getQtStockProduct(),produit.getListImages(), new CategorieDto(produit.getCategorie())));

            }
        

        return productsDto.size() > 0 ? productsDto : null; 
	}

}
