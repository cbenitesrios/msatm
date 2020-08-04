package com.everis.msatm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data 
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document
public class Operation {
	
	@Id
	private String id;
	private String operationdesc;
	private String atmid; 
	private Double amount;
	private Double commissions;
	private String transactid;
	private String productid;
	
}
