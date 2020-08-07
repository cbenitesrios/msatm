package com.everis.msatm.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class Atmrequest { 
	
  @NotNull(message = "Atm code no puede ser vacio") 	
  private String atmid;
  @NotNull(message = "Titular no puede ser vacio") 
  private String titular; 
  
  @NotNull(message = "Id del producto no puede ser vacio") 
  private String productid;
  
  @Positive(message = "El monto tiene que ser positivo")
  private Double amount;	
}
