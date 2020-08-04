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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString 
public class Atm{
	@Id
	private String id;
	private String atmcode;
	private String bank;
	private Long maxwithdrawtimes;
	private Long maxdeposittimes;
	private Double commissionwithdraw;
	private Double commissiondeposit;
	
}
