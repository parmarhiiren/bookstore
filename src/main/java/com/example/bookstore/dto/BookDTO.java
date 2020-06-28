package com.example.bookstore.dto;

import java.util.HashSet;
import java.util.Set;

public class BookDTO {

	private Long bookId;
	
	private String category;
	
	private String title;
	
	private String language;
	
	private String year;
	
	private double price;

	private Set<AuthorDTO> authors = new HashSet<AuthorDTO>();

	public BookDTO() {
	}
	
	public BookDTO(Long bookId, String category, String title, String language, String year, double price) {
		super();
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.language = language;
		this.year = year;
		this.price = price;
	}
	
	public BookDTO(Long bookId, String category, String title, String language, String year, double price, Set<AuthorDTO> authors) {
		super();
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.language = language;
		this.year = year;
		this.price = price;
		this.authors = authors;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<AuthorDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<AuthorDTO> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", category=" + category + ", title=" + title + ", language=" + language
				+ ", year=" + year + ", price=" + price + ", authors=" + authors + "]";
	}
	
}
