package com.technicalyorker.deal.dealservice.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicalyorker.deal.dealservice.service.ProductService;
import com.technicalyorker.deal.domain.Product;

@RestController
@RequestMapping("/")
class ProductEndPointImpl implements ProductEndPoint {
	@Autowired
	ProductService productService;

	@Override
	@RequestMapping("/products")
	public List<Product> products() {
		return productService.products();
	}
}
