package org.basma.store.services;

import org.basma.store.entities.Cart;
import org.basma.store.entities.ProductEntity;
import org.basma.store.shared.dto.AddToCartDto;
import org.basma.store.shared.dto.CartCost;

public interface CartService {

	public void addToCart(AddToCartDto addToCartDto,String userId);
	public CartCost listCartItems(String user_id);
	public void updateCartItem(AddToCartDto cartDto,String userId,long idItem);
	public void deleteCartItem(long id,String userId);
	
	
	
}
