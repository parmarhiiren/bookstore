package com.example.bookstore.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class Author {

	@Id
	@Column(name = "AUTHOR_ID")
	@GeneratedValue
	private Long authorId;

	@Column(name = "NAME")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "authors")
	private Set<Book> books = new HashSet<Book>();

	public Author() {
	}

	public Author(String name) {
		super();
		this.name = name;
	}

	public Author(String name, Set<Book> books) {
		super();
		this.name = name;
		this.books = books;
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

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", name=" + name + ", books=" + books + "]";
	}

}
