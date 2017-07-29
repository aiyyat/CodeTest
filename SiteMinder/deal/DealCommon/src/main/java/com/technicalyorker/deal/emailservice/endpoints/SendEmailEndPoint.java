package com.technicalyorker.deal.emailservice.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.technicalyorker.deal.domain.Email;
import com.technicalyorker.deal.domain.SendStatus;

@RequestMapping(value = "/api")
public interface SendEmailEndPoint {

	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<SendStatus> sendEmail(@RequestBody Email email);

}
