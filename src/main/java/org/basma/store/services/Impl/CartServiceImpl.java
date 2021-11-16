package org.basma.store.services.Impl;


import java.util.ArrayList;
import java.util.List;

import org.basma.store.entities.Cart;
import org.basma.store.entities.UserEntity;
import org.basma.store.repositories.CartRepository;
import org.basma.store.repositories.ProductRepository;
import org.basma.store.repositories.UserRepository;
import org.basma.store.services.CartService;
import org.basma.store.shared.dto.AddToCartDto;
import org.basma.store.shared.dto.CartCost;
import org.basma.store.shared.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImpl implements CartService {
	private CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	private Cart getAddToCartFromDto(AddToCartDto addToCartDto, String userId) {
        Cart cart=new Cart();
        cart.setProduct(productRepository.findByIdProduct(addToCartDto.getId()));
        cart.setProductId(addToCartDto.getProductId());
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setUserId(userRepository.findByUserId(userId));
        return cart;
    }
	public int isExist(AddToCartDto addToCartDto,String userId) {
		int i=0;
		List<Cart> cartList=cartRepository.findAll();
		for(Cart cart : cartList) {
			if(cart.getUserId().getUserId().equals(userId) && addToCartDto.getProductId()==cart.getProductId())i++;
		}
		return i;
	}
	
	public void addToCart(AddToCartDto addToCartDto,String userId){
		if(cartRepository.existsByUserIdAndProductId(userRepository.findByUserId(userId), addToCartDto.getProductId())) {
			Cart cart = cartRepository.findByUserIdAndProductId(userRepository.findByUserId(userId), addToCartDto.getProductId());
			cart.setQuantity(cart.getQuantity()+1);
			cartRepository.save(cart);
			System.out.println("if");
			
		}else {
			System.out.println("else");
		Cart cart = getAddToCartFromDto(addToCartDto, userId);
		cartRepository.save(cart);
		}
		
        
    }

    public CartCost listCartItems(String user_id) {
        List<Cart> cartList = cartRepository.findAllByUserIdOrderByCreatedDateDesc(user_id);
        List<CartDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartDto cartDto = getDtoFromCart(cart);
            cartItems.add(cartDto);
        }
        double totalCost = 0;
        for (CartDto cartDto:cartItems){
            totalCost += (cartDto.getProduct().getPrixProduct()* cartDto.getQuantity());
        }
        CartCost cartCost = new CartCost(cartItems,totalCost);
        return cartCost;
    }


    public static CartDto getDtoFromCart(Cart cart) {
        CartDto cartDto = new CartDto(cart);
        return cartDto;
    }


    public void updateCartItem(AddToCartDto cartDto,String userId,long idItem){
        Cart cart=cartRepository.findByid(idItem);

        if(cart.getUserId().getUserId().equals(userId)){
        	  cart.setQuantity(cartDto.getQuantity());cartRepository.save(cart);
        }
        else System.err.println("this product not fond");

                
    }

    public void deleteCartItem(long id,String userId){
    	UserEntity entity=userRepository.findByUserId(userId);
        Cart cart=cartRepository.findByUserIdAndProductId(entity, id);
        if(cart==null)System.out.println("this product n'existe pas");
        else cartRepository.delete(cart);
    }
	

	
}
