package com.springboot.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class OrderItem{

	@Id
    @GeneratedValue
    private Long id;
	private String bookId;
   	private int quantity; 
	private BigDecimal  totalPrice;
}
