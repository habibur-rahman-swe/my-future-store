package com.hr.ecom.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hr.ecom.dto.CartItemRequest;
import com.hr.ecom.model.CartItem;
import com.hr.ecom.model.Product;
import com.hr.ecom.model.User;
import com.hr.ecom.repository.CartItemRepository;
import com.hr.ecom.repository.ProductRepository;
import com.hr.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
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

	public boolean deleteItemFromCart(String userId, Long productId) {
		Optional<User> optUser = userRepository.findById(Long.valueOf(userId));
		if (optUser.isEmpty()) {
			return false;
		}
		User user = optUser.get();
		Optional<Product> optProduct = productRepository.findById(productId);
		if (optProduct.isEmpty()) {
			return false;
		}
		Product product = optProduct.get();
		
		if (user != null && product != null) {
			cartItemRepository.deleteByUserAndProduct(user, product);
			return true;
		}
		return false;
	}

	public List<CartItem> getCart(String userId) {
		return userRepository.findById(Long.valueOf(userId))
				.map(cartItemRepository::findByUser)
				.orElseGet(List::of);
	}

}
