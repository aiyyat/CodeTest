package com.technicalyorker.deal.dealservice.endpoint;

import org.springframework.http.ResponseEntity;

import com.technicalyorker.deal.dealservice.vo.QuoteRequest;
import com.technicalyorker.deal.domain.SendStatus;

public interface QuoteEndPoint {

	ResponseEntity<SendStatus> quote(QuoteRequest input);

}
