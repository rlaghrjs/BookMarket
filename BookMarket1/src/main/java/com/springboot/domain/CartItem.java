package com.springboot.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
@Data
@ToString

public class CartItem  implements Serializable {
    private static final long serialVersionUID = 3636831123198280235L;

    @Id
   	private Long id;

	private Book book;

	private int quantity;

	private BigDecimal  totalPrice;

	public CartItem(Book book) {
		super();
		this.book = book;
		this.quantity = 1;
		this.totalPrice = book.getUnitPrice();
	}

	public void setBook(Book book) {
		this.book = book;
		this.updateTotalPrice();
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}

	public void updateTotalPrice() {
		totalPrice =  this.book.getUnitPrice().multiply(new BigDecimal(this.quantity));
	}

}
