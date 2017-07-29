package com.technicalyorker.deal.dealservice.service;

import org.springframework.http.ResponseEntity;

import com.technicalyorker.deal.dealservice.vo.QuoteRequest;
import com.technicalyorker.deal.domain.SendStatus;

public interface QuoteService {

	ResponseEntity<SendStatus> quote(QuoteRequest input);

}
