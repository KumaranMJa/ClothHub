package com.backend.clothing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.clothing.model.Product;
import com.backend.clothing.repository.ProductRepository;

@Service
public class ProductService {
		
		@Autowired
		private ProductRepository productRepository;
		
		public Product saveProduct(Product product) {
			return productRepository.save(product);
		}
		
		public List<Product> getAllProducts(){
			return productRepository.findAll();
		}
		
		public Product getProductById(Long productId){
			return productRepository.findById(productId).orElse(null);
		}
		
		public Product updateProduct(Long productId, Product product) {
			Product existingProduct = productRepository.findById(productId).orElse(null);
			if(existingProduct != null) {
				existingProduct.setProductName(product.getProductName());
				existingProduct.setProductDescription(product.getProductDescription());
				existingProduct.setProductPrice(product.getProductPrice());
				existingProduct.setProductQuantity(product.getProductQuantity());
				existingProduct.setImageUrl(product.getImageUrl());
				return productRepository.save(existingProduct);
			}
			throw new RuntimeException("No Product is found at the ID to update");

		}
		
		public void deleteProduct(Long productId){
			if(productRepository.existsById(productId)) {
				productRepository.deleteById(productId);
			}
			else {
				throw new RuntimeException("No Prouct is found with such ID to Delete");
			}
		}
}

