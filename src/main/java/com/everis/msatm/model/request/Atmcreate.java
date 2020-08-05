package com.everis.msatm.model.request;


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
	private String atmcode;
	private String bank;
}
