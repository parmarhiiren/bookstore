package com.example.bookstore.input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bookstore")
public class BookStore {

    private List<BookCriteria> books;

    public List<BookCriteria> getBooks() {
        return books;
    }

    @XmlElement(name = "book")
    public void setBooks(List<BookCriteria> books) {
        this.books = books;
    }

    public void add(BookCriteria book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        this.books.add(book);
    }

	@Override
	public String toString() {
		return "Books [books=" + books + "]";
	}
    
    
}
