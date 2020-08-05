package com.everis.msatm.model.request;

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
	private String bank;
	private Double withdrawcommission;
	private Double depositcommission;
}
