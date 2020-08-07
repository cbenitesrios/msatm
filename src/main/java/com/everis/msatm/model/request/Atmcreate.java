package com.everis.msatm.model.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atmcreate {
	
	@NotNull(message = "Atm code no puede ser vacio") 
	private String atmcode;
	@NotNull(message = "Bank code no puede ser vacio") 
	private String bank;
}
