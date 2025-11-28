package com.springboot.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.validator.BookId;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Book  implements Serializable {

	private static final long serialVersionUID = -7715651009026349175L;
	@BookId
	@Pattern(regexp="ISBN[1-9]+", message="{Pattern.book.bookId}")

	private String bookId; 
	@Size(min=4, max=50, message="{Size.book.name}")
	private String name;
	@Min(value=0, message="{Min.book.unitPrice}")
	@Digits(integer=8, fraction=2, message="{Digits.book.unitPrice}")
	@NotNull(message="{NotNull.book.unitPrice}")
	private BigDecimal unitPrice; 
	private String author; 
	private String description; 
	private String publisher; 
	private String category; 
	private long unitsInStock; 
	private String releaseDate; 
	private String condition;
	private String fileName; 
	private MultipartFile bookImage;
}
