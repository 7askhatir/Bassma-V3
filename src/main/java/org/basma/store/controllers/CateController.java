package org.basma.store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.basma.store.entities.UserEntity;
import org.basma.store.services.CartService;
import org.basma.store.shared.dto.AddToCartDto;
import org.basma.store.shared.dto.CartCost;
import org.basma.store.shared.dto.CartDto;
import org.basma.store.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CateController {

	@Autowired
	CartService cartService;

	@PostMapping("/add")
	public String addToCart(@RequestBody AddToCartDto addToCartDto, Authentication authentication) {
		UserDto user = (UserDto) authentication.getPrincipal();
		String userId = user.getUserId();
		cartService.addToCart(addToCartDto, userId);
		return "added";
	}

	@DeleteMapping(path = "/{cartItemId}")
	public ResponseEntity<Object> deleteCartItem(@PathVariable("cartItemId") long itemID,
			Authentication authentication) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		String userId = userDto.getUserId();
		cartService.deleteCartItem(itemID, userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/")
	@ResponseBody
	public List<CartDto> getAllCart(Authentication authentication) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		String userId = userDto.getUserId();
		CartCost cartCost = cartService.listCartItems(userId);
		return cartCost.getcartItems();

	}
	

	@PutMapping("/update/{cartItemId}")
	public String updateCartItem(@RequestBody @Valid AddToCartDto cartDto, @PathVariable("cartItemId") long itemID,
			Authentication authentication) {
	UserDto userDto = (UserDto) authentication.getPrincipal();
		String userId = userDto.getUserId();
		cartService.updateCartItem(cartDto, userId, itemID);
		return "update";
	}

}
