package com.everis.msatm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.msatm.model.Atm;

public interface IAtmrepo extends ReactiveMongoRepository<Atm, String> {

}
