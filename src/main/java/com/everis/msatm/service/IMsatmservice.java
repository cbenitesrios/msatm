package com.everis.msatm.service;
  

import org.springframework.web.reactive.function.client.WebClient;

import com.everis.msatm.model.Atm;
import com.everis.msatm.model.Bankcommission;
import com.everis.msatm.model.Operation;
import com.everis.msatm.model.request.Atmcreate;
import com.everis.msatm.model.request.Atmrequest;
import com.everis.msatm.model.request.BankcommissionCreate;

import reactor.core.publisher.Mono;

public interface IMsatmservice { 
	Mono<Operation> depositatm(Atmrequest atmdepositreq, WebClient transactwc);
	Mono<Operation> withdrawatm(Atmrequest atmwithdrawreq, WebClient transactwc); 
	Mono<Atm> createatm(Atmcreate atm);
	Mono<Bankcommission> createbankcommission(BankcommissionCreate bankcommission);
}
