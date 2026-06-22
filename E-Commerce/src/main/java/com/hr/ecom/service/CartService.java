package com.hr.ecom.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hr.ecom.dto.CartItemRequest;
import com.hr.ecom.model.CartItem;
import com.hr.ecom.model.Product;
import com.hr.ecom.model.User;
import com.hr.ecom.repository.CartItemRepository;
import com.hr.ecom.repository.ProductRepository;
import com.hr.ecom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final ProductRepository productRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	
	public boolean addToCart(String userId, CartItemRequest request) {
		Optional<Product> optProduct = productRepository.findById(request.getProductId());
		
		if (optProduct.isEmpty()) {
			return false;
		}
		
		Product product = optProduct.get(); 
		if (product.getStockQuantity() < request.getQuantity()) {
			return false;
		}
		
		Optional<User> optUser = userRepository.findById(Long.valueOf(userId));
		if (optUser.isEmpty()) {
			return false;
		}
		User user = optUser.get();
		
		CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
		if (existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
			existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
			cartItemRepository.save(existingCartItem);
		} else {
			CartItem cartItem = new CartItem();
			cartItem.setUser(user);
			cartItem.setProduct(product);
			cartItem.setQuantity(request.getQuantity());
			cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
			cartItemRepository.save(cartItem);
		}
		return true;
	}

}
