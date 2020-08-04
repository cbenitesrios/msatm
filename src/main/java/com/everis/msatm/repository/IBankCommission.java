package com.everis.msatm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository; 
import com.everis.msatm.model.BankCommission;

import reactor.core.publisher.Mono;

public interface IBankCommission extends ReactiveMongoRepository<BankCommission, String> {
  public Mono<BankCommission> findByBank(String bank);
}
