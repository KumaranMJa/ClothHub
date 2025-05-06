package com.backend.clothing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.clothing.model.Product;
import com.backend.clothing.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/admin")
	public ResponseEntity<?> createProduct(@RequestBody Product product){
		try {
			Product savedProduct = productService.saveProduct(product);
			return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
		}
		catch(RuntimeException e) {
			return new ResponseEntity<>(e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getAllProduct(){
		try {
			List<Product> products = productService.getAllProducts();
			if(!products.isEmpty()) {
				return new ResponseEntity<>(products, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("No Products is Found", HttpStatus.NOT_FOUND);
			}
		}
		catch(RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable Long productId){
		try {
			Product requestedProduct = productService.getProductById(productId);
			if(requestedProduct != null) {
				return new ResponseEntity<>(requestedProduct, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("No Product is Found on the Id", HttpStatus.NOT_FOUND);
			}
		}
		catch(RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/admin/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable Long productId , @RequestBody Product product){
		try {
			Product updatedProduct = productService.updateProduct(productId, product);
			return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
		}
		catch(RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/admin/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
		try {
			productService.deleteProduct(productId);
			return new ResponseEntity<>("Product is Deleted",HttpStatus.OK);
		}
		catch(RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}


