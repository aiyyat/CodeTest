package com.technicalyorker.deal.dealservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicalyorker.deal.dealservice.service.QuoteService;
import com.technicalyorker.deal.dealservice.vo.QuoteRequest;
import com.technicalyorker.deal.domain.SendStatus;

@RestController
@RequestMapping("/")
public class QuoteEndPointImpl implements QuoteEndPoint {
	@Autowired
	QuoteService quoteService;

	@Override
	@RequestMapping(path = "/quote", consumes = "application/json")
	public ResponseEntity<SendStatus> quote(@RequestBody QuoteRequest input) {
		return quoteService.quote(input);
	}
}
