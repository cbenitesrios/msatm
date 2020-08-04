package com.everis.msatm.service.impl;

import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.everis.msatm.customexception.Atmexception;
import com.everis.msatm.model.Operation;
import com.everis.msatm.model.dto.AtmtransactDto;
import com.everis.msatm.model.request.Atmrequest; 
import com.everis.msatm.model.response.TransactionResponse;
import com.everis.msatm.repository.IAtmrepo;
import com.everis.msatm.repository.IBankCommission;
import com.everis.msatm.repository.IOperationrepo;
import com.everis.msatm.service.IMsatmservice;

import reactor.core.publisher.Mono;

public class Msatmserviceimpl implements IMsatmservice{

	@Autowired
	IAtmrepo atmrepo;
	@Autowired
	IOperationrepo operationrepo;
	@Autowired
	IBankCommission bankcommrepo;
	
	public Mono<Operation> depositatm(Atmrequest atmdeporequest, WebClient transactwc){
	  return atmrepo.findById(atmdeporequest.getAtmid()).switchIfEmpty(Mono.error(Atmexception.builder()
                                                        .code("Error-1")
                                                        .message(MessageFormat.format("Error no se encontro ATM {0}.", atmdeporequest.getAtmid()))
                                                        .status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
              .flatMap(atm-> bankcommrepo.findByBank(atm.getBank())
            		  .switchIfEmpty(Mono.error(Atmexception.builder()
                      .code("Error-2")
                      .message(MessageFormat.format("Error no se encontro banco {0}.", atm.getBank()))
                      .status(HttpStatus.INTERNAL_SERVER_ERROR)
                      .build()))
            		  .flatMap(commission-> transactwc.post().body(BodyInserters.fromValue(
                                    		           AtmtransactDto.builder()
            			                               .amount(atmdeporequest.getAmount())
            			                               .atmbank(atm.getBank())
            			                               .commission(commission.getDepositcommission())
            			                               .build()))
            				                           .retrieve()
            				                           .bodyToMono(TransactionResponse.class)))
              .map(response-> Operation.builder()
            		                   .amount(atmdeporequest.getAmount())
            		                   .operationdesc("DEPOSIT")
            		                   .atmid(atmdeporequest.getAtmid())
            		                   .amount(response.getAmount())
            		                   .commissions(response.getTotalcommission())
            		                   .build());
	}
	  public Mono<Operation> withdrawatm(Atmrequest atmwithrequest, WebClient transactwc) {
		  return atmrepo.findById(atmwithrequest.getAtmid()).switchIfEmpty(Mono.error(Atmexception.builder()
	                                                        .code("Error-1")
	                                                        .message(MessageFormat.format("Error no se encontro ATM {0}.", atmwithrequest.getAtmid()))
	                                                        .status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
	              .flatMap(atm-> bankcommrepo.findByBank(atm.getBank())
	            		  .switchIfEmpty(Mono.error(Atmexception.builder()
	                      .code("Error-2")
	                      .message(MessageFormat.format("Error no se encontro banco {0}.", atm.getBank()))
	                      .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                      .build()))
	            		  .flatMap(commission-> transactwc.post().body(BodyInserters.fromValue(
	                                    		           AtmtransactDto.builder()
	            			                               .amount(atmwithrequest.getAmount())
	            			                               .atmbank(atm.getBank())
	            			                               .commission(commission.getDepositcommission())
	            			                               .build()))
	            				                           .retrieve()
	            				                           .bodyToMono(TransactionResponse.class)))
	              .map(response-> Operation.builder()
		                                   .amount(atmwithrequest.getAmount())
		                                   .operationdesc("WITHDRAW")
		                                   .atmid(atmwithrequest.getAtmid())
		                                   .amount(response.getAmount())
		                                   .commissions(response.getTotalcommission())
		                                   .build()); 
	}
}
