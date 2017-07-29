package com.technicalyorker.deal.emailservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.technicalyorker.deal.domain.Email;
import com.technicalyorker.deal.domain.SendStatus;
import com.technicalyorker.deal.domain.Status;
import com.technicalyorker.deal.emailservice.service.EmailSenderService;

import io.swagger.annotations.Api;

@RestController
@Api
public class SendEmailEndPointImpl implements SendEmailEndPoint {
	@Autowired
	EmailSenderService emailService;

	@Override
	public ResponseEntity<SendStatus> sendEmail(@RequestBody Email email) {
		SendStatus s = new SendStatus(Status.EMAIL_SEND_SUCCESS);
		emailService.send(email);
		return new ResponseEntity<SendStatus>(s, HttpStatus.OK);
	}
}
