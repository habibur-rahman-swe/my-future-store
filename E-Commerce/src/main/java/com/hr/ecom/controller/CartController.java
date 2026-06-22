package com.hr.ecom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.ecom.dto.CartItemRequest;
import com.hr.ecom.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	
	@PostMapping
	public ResponseEntity<String> addToCart(
			@RequestHeader("X-User-ID") String userId,
			@RequestBody CartItemRequest request
			){
		if(!cartService.addToCart(userId, request)) {
			return ResponseEntity.badRequest().body("Product out of stock or User not found or product not found");
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
