package com.everis.msatm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository; 
import com.everis.msatm.model.Bankcommission;

import reactor.core.publisher.Mono;

public interface IBankcommission extends ReactiveMongoRepository<Bankcommission, String> {
  public Mono<Bankcommission> findByBank(String bank);
}
