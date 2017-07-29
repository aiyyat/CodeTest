package com.technicalyorker.deal.emailservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.technicalyorker.deal.domain.Email;
import com.technicalyorker.deal.domain.SendStatus;
import com.technicalyorker.deal.domain.Status;
import com.technicalyorker.deal.emailservice.model.SendGridEmailBuilder;
import com.technicalyorker.deal.emailservice.model.SendGridEmailBuilder.SendGridEmail;
import com.technicalyorker.deal.emailservice.qualifier.GunMail;
import com.technicalyorker.deal.emailservice.qualifier.SendGrid;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
	@Value("${mailgun.from}")
	private String from;
	@Value("${mailgun.endpointuri}")
	private String mailGunEndPointURI;
	@Value("${sendGrid.endpointuri}")
	private String sendGridEndPointURI;
	@Autowired
	@GunMail
	private RestTemplate gunMailRestTemplate;
	
	@Autowired
	@SendGrid
	private RestTemplate sendGridRestTemplate;
	ObjectMapper mapper = new ObjectMapper();
	private final Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);

	public SendStatus fallbackSend(Email email) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			SendGridEmail sendGridEmail = new SendGridEmailBuilder().email(email).build();
			String payload = mapper.writeValueAsString(sendGridEmail);
			logger.info(payload);
			ResponseEntity<String> response = sendGridRestTemplate.postForEntity(sendGridEndPointURI,
					new HttpEntity<String>(payload, headers), String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				return new SendStatus(Status.EMAIL_SEND_SUCCESS);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new SendStatus(Status.EMAIL_SEND_FAILURE);
	}

	@Override
	@HystrixCommand(fallbackMethod = "fallbackSend")
	public SendStatus send(Email email) {
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			HttpHeaders headers = new HttpHeaders();
			map.add("from", from);
			map.add("to", email.getTo());
			map.add("cc", email.getFrom());
			map.add("bcc", email.getAdminEmail());
			map.add("subject", email.getSubject());
			map.add("text", email.getMessage() + "\n sent using MailGun");
			gunMailRestTemplate.exchange(mailGunEndPointURI, HttpMethod.POST, new HttpEntity<>(map, headers),
					String.class);
			return new SendStatus(Status.EMAIL_SEND_SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
