package com.example.bookstore.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "BOOK")
public class Book {

	@Id
	@Column( name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	
	@Column( name = "CATEGORY")
	private String category;
	
	@Column( name = "TITLE")
	private String title;
	
	@Column( name = "LANGUAGE")
	private String language;
	
	@Column( name = "YEAR")
	private String year;
	
	@Column( name = "PRICE")
	private double price;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
    })
    @JoinTable(
        name = "BOOKS_AUTHORS",
        joinColumns = {
            @JoinColumn(name = "BOOK_ID")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "AUTHOR_ID")
        }
	)
	private Set<Author> authors = new HashSet<Author>();

	public Book() {
	}
	
	public Book(String category, String title, String language, String year, double price) {
		super();
		this.category = category;
		this.title = title;
		this.language = language;
		this.year = year;
		this.price = price;
	}
	
	public Book(String category, String title, String language, String year, double price, Set<Author> authors) {
		super();
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

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", category=" + category + ", title=" + title + ", language=" + language
				+ ", year=" + year + ", price=" + price + "]";
	}

	
}
