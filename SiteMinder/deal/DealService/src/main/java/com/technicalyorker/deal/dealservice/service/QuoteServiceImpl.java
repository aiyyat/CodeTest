package com.technicalyorker.deal.dealservice.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.technicalyorker.deal.dealservice.repository.ProductRepository;
import com.technicalyorker.deal.dealservice.vo.QuoteRequest;
import com.technicalyorker.deal.domain.Email;
import com.technicalyorker.deal.domain.Product;
import com.technicalyorker.deal.domain.SendStatus;
import com.technicalyorker.deal.domain.Status;
import com.technicalyorker.deal.domain.Store;
import com.technicalyorker.deal.emailservice.endpoints.SendEmailEndPoint;

@Service
public class QuoteServiceImpl implements QuoteService {
	@Autowired
	ProductRepository productRepo;

	@Autowired
	SendEmailEndPointFeignClient sendEmailEndPoint;

	private final Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);

	@Override
	public ResponseEntity<SendStatus> quote(@RequestBody QuoteRequest input) {
		try {
			logger.debug("sendEmailEndPoint:" + sendEmailEndPoint);
			logger.info("Quote Request: " + input);
			Map<Store, StringBuilder> storeProducts = new HashMap<>();
			for (Long productId : input.getProductIds()) {
				Product product = productRepo.findOne(productId);
				product.getStore().forEach(store -> {
					if (storeProducts.get(store) == null) {
						storeProducts.put(store, new StringBuilder());
					}
					storeProducts.get(store).append("* ").append(product.getProductName()).append("\n");
				});
			}
			logger.debug("storeProducts: " + storeProducts);
			for (Store store : storeProducts.keySet()) {
				StringBuilder sb = new StringBuilder();
				sb.append("Dear Mr. Manager, \n").append(store.getStoreName()).append("Store. \n\nI,")
						.append(input.getUsername()).append(", EmailId: ").append(input.getEmailId())
						.append(" for the purpose: ").append(input.getPurpose())
						.append(" would like to have a quote of the best possible Deals from your store for the following items: \n")
						.append(storeProducts.get(store)).append("\nRegards,\n").append(input.getUsername());
				logger.info(store.toString());
				Email email = new Email();
				email.setTo(store.getEmailId());
				email.setFrom(input.getEmailId());
				email.setMessage(sb.toString());
				email.setSubject("Best Deal, Quote Request: " + input.getUsername());
				sendEmailEndPoint.sendEmail(email).getBody();
			}
			return new ResponseEntity<SendStatus>(new SendStatus(Status.EMAIL_SEND_SUCCESS), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SendStatus>(new SendStatus(Status.EMAIL_SEND_FAILURE), HttpStatus.OK);
	}
}

@FeignClient(value = "customer-service", url = "${email.url}")
interface SendEmailEndPointFeignClient extends SendEmailEndPoint {
}
