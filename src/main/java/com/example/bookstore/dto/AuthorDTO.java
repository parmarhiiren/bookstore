package com.example.bookstore.dto;

import java.util.HashSet;
import java.util.Set;

public class AuthorDTO {

	private Long authorId;

	private String name;
	
	private Set<BookDTO> books = new HashSet<BookDTO>();

	public AuthorDTO() {
	}

	public AuthorDTO(Long authorId, String name) {
		super();
		this.authorId = authorId;
		this.name = name;
	}
	
	public AuthorDTO(Long authorId, String name, Set<BookDTO> books) {
		super();
		this.authorId = authorId;
		this.name = name;
		this.books = books;
	}
	
	public AuthorDTO(String name) {
		super();
		this.name = name;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(Set<BookDTO> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "AuthorDTO [authorId=" + authorId + ", name=" + name + ", books=" + books + "]";
	}

	

}
