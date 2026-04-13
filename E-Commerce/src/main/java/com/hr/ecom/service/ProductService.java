package com.hr.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hr.ecom.dto.ProductRequest;
import com.hr.ecom.dto.ProductResponse;
import com.hr.ecom.model.Product;
import com.hr.ecom.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = new Product();

		updateProductFromRequest(product, productRequest);

		Product savedProduct = productRepository.save(product);

		return mapToProductResponse(savedProduct);
	}

	public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
		return productRepository.findById(id).map(existingProduct -> {
			updateProductFromRequest(existingProduct, productRequest);
			productRepository.save(existingProduct);
			return mapToProductResponse(existingProduct);
		});
	}
	
	public List<ProductResponse> getAllProducts() {
		return productRepository.findByActiveTrue().stream().map(this::mapToProductResponse).toList();
	}

	private ProductResponse mapToProductResponse(Product savedProduct) {
		ProductResponse response = new ProductResponse();
		response.setId(savedProduct.getId());
		response.setName(savedProduct.getName());
		response.setDescription(savedProduct.getDescription());
		response.setCategory(savedProduct.getCategory());
		response.setPrice(savedProduct.getPrice());
		response.setImageUrl(savedProduct.getImageUrl());
		response.setActive(savedProduct.getActive());
		response.setStockQuantity(savedProduct.getStockQuantity());
		return response;
	}

	private void updateProductFromRequest(Product product, ProductRequest productRequest) {
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setCategory(productRequest.getCategory());
		;
		product.setPrice(productRequest.getPrice());
		product.setImageUrl(productRequest.getImageUrl());
		product.setStockQuantity(productRequest.getStockQuantity());
	}

	public boolean deleteProduct(Long id) {
		return productRepository.findById(id)
				.map(product -> {
					productRepository.delete(product);
					return true;
				}).orElse(false);
	}

	public List<ProductResponse> searchProducts(String keyword) {
		return productRepository.searchProducts(keyword).stream().map(this::mapToProductResponse).toList();
	}
}
