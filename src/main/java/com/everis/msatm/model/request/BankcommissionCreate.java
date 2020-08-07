package com.everis.msatm.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BankcommissionCreate { 
	
	@NotNull(message = "El campo Banco no puede ser vacio") 
	private String bank;
	
	@Positive(message = "El campo withdrawcommission no puede ser vacio") 
	private Double withdrawcommission;
	
	@Positive(message = "El campo depositcommission no puede ser vacio") 
	private Double depositcommission;
}
