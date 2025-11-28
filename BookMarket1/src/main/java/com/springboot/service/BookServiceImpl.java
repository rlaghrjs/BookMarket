package com.springboot.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.Book;
import com.springboot.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBookList() {
		return bookRepository.getAllBookList();
	}

	@Override
	public List<Book> getBookListByCategory(String category) {
	      List<Book> booksByCategory = bookRepository.getBookListByCategory(category);
	      return booksByCategory;
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
	      Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
	      return booksByFilter;
	}

	@Override
	public Book getBookById(String bookId) {
	     Book bookById = bookRepository.getBookById(bookId);
	     return bookById;
	}
	 @Override
	public void setNewBook(Book book) {
		    bookRepository.setNewBook(book);
		 }

	 @Override
	public void setUpdateBook(Book book) {
	      bookRepository.setUpdateBook(book);
	 }

	 @Override
	public void setDeleteBook(String bookID) {
	      bookRepository.setDeleteBook(bookID);
	   }
}
