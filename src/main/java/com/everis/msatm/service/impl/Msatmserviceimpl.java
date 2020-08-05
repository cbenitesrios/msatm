package com.everis.msatm.service.impl;

import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;  
import com.everis.msatm.customexception.Atmexception;
import com.everis.msatm.model.Atm;
import com.everis.msatm.model.Bankcommission;
import com.everis.msatm.model.Operation;
import com.everis.msatm.model.dto.AtmtransactDto;
import com.everis.msatm.model.request.Atmcreate;
import com.everis.msatm.model.request.Atmrequest;
import com.everis.msatm.model.request.BankcommissionCreate;
import com.everis.msatm.model.response.TransactionResponse;
import com.everis.msatm.repository.IAtmrepo;
import com.everis.msatm.repository.IBankcommission;
import com.everis.msatm.repository.IOperationrepo;
import com.everis.msatm.service.IMsatmservice;

import reactor.core.publisher.Mono;

@Service
public class Msatmserviceimpl implements IMsatmservice{

	@Autowired
	IAtmrepo atmrepo;
	@Autowired
	IOperationrepo operationrepo;
	@Autowired
	IBankcommission bankcommrepo;
	
	
	@Override
	public Mono<Operation> depositatm(Atmrequest atmdeporequest, WebClient transactwc){
	  return atmrepo.findById(atmdeporequest.getAtmid()).switchIfEmpty(Mono.error(Atmexception.builder()
                                                        .code("Error-1")
                                                        .message(MessageFormat.format("Error no se encontro ATM {0}.", atmdeporequest.getAtmid()))
                                                        .status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
              .flatMap(atm-> bankcommrepo.findByBank(atm.getBank()).doOnNext(System.out::println)
            		  .switchIfEmpty(Mono.error(Atmexception.builder()
                      .code("Error-2")
                      .message(MessageFormat.format("Error no se encontro banco {0}.", atm.getBank()))
                      .status(HttpStatus.INTERNAL_SERVER_ERROR)
                      .build()))
            		  .flatMap(commission-> transactwc.post().body(BodyInserters.fromValue(
                                    		           AtmtransactDto.builder()
                                    		           .productid(atmdeporequest.getProductid())
                                    		           .titular(atmdeporequest.getTitular())
            			                               .amount(atmdeporequest.getAmount())
            			                               .atmbank(atm.getBank())
            			                               .commission(commission.getDepositcommission())
            			                               .build()))
            				                           .retrieve()
            				                           .bodyToMono(TransactionResponse.class)))
              .flatMap(response-> operationrepo.save(Operation.builder()
            		                   .amount(atmdeporequest.getAmount())
            		                   .operationdesc("DEPOSIT")
            		                   .atmid(atmdeporequest.getAtmid())
            		                   .transactid(response.getTransactid())
            		                   .productid(response.getProductid())
            		                   .amount(response.getAmount())
            		                   .commissions(response.getTotalcommission())
            		                   .build()));
	}
	  @Override
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
               		                                              .productid(atmwithrequest.getProductid())
               		                                              .titular(atmwithrequest.getTitular())
               		                                              .amount(atmwithrequest.getAmount())
               		                                              .atmbank(atm.getBank())
               		                                              .commission(commission.getDepositcommission())
               		                                              .build()))
	                                                              .retrieve()
	                                                              .bodyToMono(TransactionResponse.class)))
	              .flatMap(response-> operationrepo.save(Operation.builder()
		                   .amount(atmwithrequest.getAmount())
		                   .operationdesc("WITHDRAW")
		                   .atmid(atmwithrequest.getAtmid())
		                   .transactid(response.getTransactid())
		                   .productid(response.getProductid())
		                   .amount(response.getAmount())
		                   .commissions(response.getTotalcommission())
		                   .build()));
	}
	  
	  @Override
	  public Mono<Atm> createatm(Atmcreate atm){
	      return atmrepo.save(Atm.builder().bank(atm.getBank()).atmcode(atm.getAtmcode()).build());
	  }
	  @Override
	  public Mono<Bankcommission> createbankcommission(BankcommissionCreate bankcommission){
		  return bankcommrepo.save(Bankcommission.builder()
				           .bank(bankcommission.getBank())
				           .withdrawcommission(bankcommission.getWithdrawcommission())
				           .depositcommission(bankcommission.getDepositcommission())
				           .build());
	}
	  
	 
	  
}
