package com.everis.msatm.service;
  

import org.springframework.web.reactive.function.client.WebClient;

import com.everis.msatm.model.Operation;
import com.everis.msatm.model.request.Atmrequest;

import reactor.core.publisher.Mono;

public interface IMsatmservice { 
	Mono<Operation> depositatm(Atmrequest atmdepositreq, WebClient transactwc);
}
