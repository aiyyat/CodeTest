package com.technicalyorker.deal.dealservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicalyorker.deal.dealservice.repository.ProductRepository;
import com.technicalyorker.deal.domain.Product;

@Service
class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepo;

	@Override
	public List<Product> products() {
		return productRepo.findAll();
	}
}
