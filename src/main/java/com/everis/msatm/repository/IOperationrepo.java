package com.everis.msatm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository; 
import com.everis.msatm.model.Operation;

public interface IOperationrepo extends ReactiveMongoRepository<Operation, String> {

}
