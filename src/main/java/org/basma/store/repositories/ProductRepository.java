package org.basma.store.repositories;

import org.basma.store.entities.ProductEntity; 

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>{
	
	ProductEntity findByProductId(String titleProduct);
	ProductEntity findByIdProduct(long id);
	ProductEntity findByTitleProduct(String titleProduct);
}