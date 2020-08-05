package com.everis.msatm.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.everis.msatm.customexception.Atmexception;
import com.everis.msatm.customexception.ErrorDto;
import com.everis.msatm.model.Atm;
import com.everis.msatm.model.Bankcommission;
import com.everis.msatm.model.Operation;
import com.everis.msatm.model.request.Atmcreate;
import com.everis.msatm.model.request.Atmrequest;
import com.everis.msatm.model.request.BankcommissionCreate;
import com.everis.msatm.service.IMsatmservice; 

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apiatm")
public class MsatmController {
	
	@Autowired
	IMsatmservice atmservice;
	@ExceptionHandler
	    public Mono<ErrorDto> exception(Atmexception exception) {  
	      return Mono.just(new ErrorDto(exception.getStatus().toString(), exception.getMessage()));
	} 
	
	public static final String URL_TRANSACT = "http://localhost:8040/apitransaction";
	
	@PostMapping("/depositatm")
    public Mono<Operation> depositatm(@RequestBody Atmrequest request){
		return atmservice.depositatm(request,  WebClient.create( URL_TRANSACT + "/depositatm"));
	}
	
	@PostMapping("/withdrawatm")
    public Mono<Operation> withdrawatm(@RequestBody Atmrequest request){
		return atmservice.withdrawatm(request,  WebClient.create( URL_TRANSACT + "/withdrawatm"));
	}
	
	@PostMapping("/createatm")
    public Mono<Atm> createatm(@RequestBody Atmcreate request){
		return atmservice.createatm(request);
	}
	
	@PostMapping("/createbankcom")
    public Mono<Bankcommission> createbankcommission(@RequestBody BankcommissionCreate request){
		return atmservice.createbankcommission(request);
	}
	
	
	
}
