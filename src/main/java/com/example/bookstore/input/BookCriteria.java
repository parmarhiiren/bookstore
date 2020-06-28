package com.example.bookstore.input;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class BookCriteria {

	private String category;
	private Title title;
	private String year;
	private double price;
	private AuthorsCriteria authors;

	public BookCriteria() {
	}

	public BookCriteria(String category, Title title, String year, double price, AuthorsCriteria authors) {
		super();
		this.category = category;
		this.title = title;
		this.year = year;
		this.price = price;
		this.authors = authors;
	}
	
	public String getCategory() {
		return category;
	}

	@XmlAttribute(name = "category")
	public void setCategory(String category) {
		this.category = category;
	}

	public String getYear() {
		return year;
	}

	@XmlElement(name = "year")
	public void setYear(String year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	@XmlElement(name = "price")
	public void setPrice(double price) {
		this.price = price;
	}

	public Title getTitle() {
		return title;
	}

	@XmlElement(name = "title")
	public void setTitle(Title title) {
		this.title = title;
	}

	public AuthorsCriteria getAuthors() {
		return authors;
	}

	@XmlElement(name = "authors")
	public void setAuthors(AuthorsCriteria authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "BookCriteria [category=" + category + ", title=" + title + ", year=" + year + ", price=" + price
				+ ", authors=" + authors + "]";
	}

}
