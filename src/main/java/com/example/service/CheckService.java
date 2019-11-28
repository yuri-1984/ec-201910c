package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.example.domain.Credit;
import com.example.domain.ReceiveCredit;
import com.example.form.OrderForm;

@Service
public class CheckService {
	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder.build();
    }
	
	public Credit check(OrderForm form,Model model) {
		Credit credit = new Credit();
		credit.setUserId(form.getUserId());
		credit.setOrderNumber(form.getOrderNumber());
		credit.setOrderAmount(form.getOrderAmount());
		credit.setCardNumber(form.getCardNumber());
		credit.setCardExpYear(form.getCardExpYear());
		credit.setCardExpMonth(form.getCardExpMonth());
		credit.setCardName(form.getCardName());
		credit.setCardCvv(form.getCardCvv());
		
		return credit;
		
		
		
	}
	
	private static final String URL ="http://192.168.56.103:8080/sample-credit-card-web-api/credit-card/payment";
	
	public boolean service(Credit credit,ReceiveCredit receiveCredit) {
		
	ReceiveCredit receiveCredit1 = restTemplate.postForObject(URL,credit,ReceiveCredit.class);
		
		if(receiveCredit1.getStatus().equals("success")) {
			return true;
			
		}else if(receiveCredit1.getStatus().equals("error")) {
			return false;
		}
		return false;
		
		
		
	}
	
	
	

}