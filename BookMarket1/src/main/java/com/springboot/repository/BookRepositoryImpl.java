package com.springboot.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Book;
import com.springboot.exception.BookIdException;

@Repository
public class BookRepositoryImpl implements BookRepository {


	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public List<Book> getAllBookList() {
		String sql = "SELECT * FROM book";
	    List<Book> listOfBooks = jdbcTemplate.query(sql, new BookRowMapper());

		return listOfBooks;
	}

	@Override
	public List<Book> getBookListByCategory(String category) {
		List<Book> booksByCategory = new ArrayList<>();
		String sql = "SELECT * FROM book where b_category LIKE '%" + category + "%'";
		booksByCategory = jdbcTemplate.query(sql, new BookRowMapper());

		return booksByCategory;
	}


	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> booksByPublisher = new HashSet<>();
		Set<Book> booksByCategory = new HashSet<>();

		Set<String> booksByFilter = filter.keySet();

		if (booksByFilter.contains("publisher")) {
			for (String pubisherName : filter.get("publisher")) {
				 String sql = "SELECT * FROM book where b_publisher LIKE '%" + pubisherName + "%'";
				 List<Book> book = jdbcTemplate.query(sql, new BookRowMapper());
			     booksByPublisher.addAll(book);
			}
		}

		if (booksByFilter.contains("category")) {
			for (String category : filter.get("category")) {
				String sql = "SELECT * FROM book where b_category LIKE '%" + category + "%'";
				List<Book> list = jdbcTemplate.query(sql, new BookRowMapper());
				booksByCategory.addAll(list);
			}
		}

		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	}

	@Override
	public Book getBookById(String bookId) {
		Book bookInfo = null;
		String sql = "SELECT count(*) FROM book where b_bookId=?";
		int rowCount = jdbcTemplate.queryForObject(sql, Integer.class, bookId);
		if (rowCount != 0) {
		    sql = "SELECT * FROM book where b_bookId=?";
		    bookInfo = jdbcTemplate.queryForObject(sql, new BookRowMapper(),bookId );
		}
		if (bookInfo== null) {
			throw new BookIdException(bookId);
		}

	     return bookInfo;
	}

	@Override
	public void setNewBook(Book book) {
		String SQL = "INSERT INTO book (b_bookId, b_name, b_unitPrice, b_author, b_description, b_publisher, b_category, b_unitsInStock, b_releaseDate,b_condition, b_fileName) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(SQL, book.getBookId(), book.getName(), book.getUnitPrice(), book.getAuthor(),
			book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(),
			book.getReleaseDate(), book.getCondition(), book.getFileName());
	}

	@Override
	public void setUpdateBook(Book book) {
		if (book.getFileName() != null) {
			String SQL = "UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, b_publisher = ?, b_category = ?, b_unitsInStock = ?,b_releaseDate = ?, b_condition = ?, b_fileName = ?  where b_bookId = ? ";
			jdbcTemplate.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(),
				book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(),
				book.getCondition(), book.getFileName(), book.getBookId());
			} else if (book.getFileName() == null) {
			String SQL = "UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, b_publisher = ?, b_category = ?, b_unitsInStock = ?, b_releaseDate = ?, b_condition = ?  where b_bookId = ? ";
			jdbcTemplate.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(),
				book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(),
				book.getCondition(), book.getBookId());
		}
	}

	@Override
	public void setDeleteBook(String bookID) {
		String SQL = "DELETE from Book where b_bookId = ? ";
		jdbcTemplate.update(SQL, bookID);
    }
}
