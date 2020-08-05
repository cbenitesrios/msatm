package com.everis.msatm.model.request;

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
  private String atmid;
  private String titular; 
  private String productid;
  private Double amount;	
}
