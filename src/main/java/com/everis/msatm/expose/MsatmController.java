package com.everis.msatm.expose;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.everis.msatm.model.Operation;
import com.everis.msatm.model.request.Atmrequest;
import com.everis.msatm.service.IMsatmservice; 

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apiatm")
public class MsatmController {
	
	@Autowired
	IMsatmservice atmservice;
	
	public static final String URL_TRANSACT = "http://localhost:8040/apitransaction";
	
	@PostMapping("/depositatm")
    public Mono<Operation> depositatm(@RequestBody Atmrequest request){
		return atmservice.depositatm(request,  WebClient.create( URL_TRANSACT + "/depositatm"));
	}
	
	@PostMapping("/withdrawatm")
    public Mono<Operation> withdrawatm(@RequestBody Atmrequest request){
		return atmservice.depositatm(request,  WebClient.create( URL_TRANSACT + "/withdrawatm"));
	}
	
	
	
	
}
