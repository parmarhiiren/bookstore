package com.example.bookstore.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.bookstore.domain.Author;
import com.example.bookstore.domain.Book;
import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;

@RestController
@RequestMapping("/api/v1")
public class BooksController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;

	/**
	 * Get all the books stored in database
	 * @return List<BookDTO>
	 */
	@GetMapping(value = "/books")
	public List<BookDTO> findAllBooks() {
		List<Book> listOfBooks = bookRepository.findAll();
		List<BookDTO> listOfBookDTO = convertToDTO(listOfBooks);
		return listOfBookDTO;
	}
	
	
	/**
	 * Get the Books based on the Id passed
	 * @param id
	 * @return BookDTO
	 */
	@GetMapping(value = "/books/{id}")
	public BookDTO findBookById(@PathVariable("id") Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent())
			return convertToDTO(book.get());
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not available for id " + id);
	}

	/**
	 * Post the Book to be updated to the database
	 * @param book
	 * @return
	 */
	@PostMapping(value = "/books")
	public BookDTO createBook(@RequestBody Book book) {
		Set<Author> updatedListOfAuthors = new HashSet<Author>();
		Set<Author> listOfAuthors = book.getAuthors();
		for(Author author: listOfAuthors) {
			Author existingAuthor = authorRepository.findByName(author.getName());
			if(existingAuthor != null && existingAuthor.getAuthorId() != null) {
				updatedListOfAuthors.add(existingAuthor);
			} else {
				updatedListOfAuthors.add(new Author(author.getName()));
			}
		}
		book.setAuthors(updatedListOfAuthors);
		Book bookReturned = bookRepository.save(book);
		return convertToDTO(bookReturned);
	}

	/**
	 * Update the book
	 * @param book
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/books/{id}")
	public BookDTO updateBook(@RequestBody Book book, @PathVariable("id") Long id) {
		Optional<Book> dbBook = bookRepository.findById(id);
		if (dbBook.isPresent()) {
			Book existingBook = dbBook.get();
			populateValues(existingBook, book);
			Book bookReturned = bookRepository.save(existingBook);
			return convertToDTO(bookReturned);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not available for id " + id);
		}
	}
	

	/**
	 * Delete the book
	 * @param id
	 */
	@DeleteMapping(value = "/books/{id}")
	public void deleteBook(@PathVariable("id") Long id) {
		Optional<Book> dbBook = bookRepository.findById(id);
		if (dbBook.isPresent()) {
			Book existingBook = dbBook.get();
			bookRepository.deleteById(existingBook.getBookId());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not available for id " + id);
		}
	}
	
	
	/**
	 * Populate the updated values 
	 * @param existingBook
	 * @param book
	 */
	private void populateValues(Book existingBook, Book book) {
		Set<Author> updatedListOfAuthors = new HashSet<Author>();
		existingBook.setCategory(book.getCategory() );
		existingBook.setLanguage(book.getLanguage());
		existingBook.setPrice(book.getPrice());
		existingBook.setTitle(book.getTitle());
		existingBook.setYear(book.getYear());
		Set<Author> listOfAuthors = book.getAuthors();
		for(Author author: listOfAuthors) {
			Author existingAuthor = authorRepository.findByName(author.getName());
			if(existingAuthor != null && existingAuthor.getAuthorId() != null) {
				updatedListOfAuthors.add(existingAuthor);
			} else {
				updatedListOfAuthors.add(new Author(author.getName()));
			}
		}
		existingBook.setAuthors(updatedListOfAuthors);
	}

	/**
	 * Converts the list of books to DTOs
	 * @param listOfBooks
	 * @return
	 */
	private List<BookDTO> convertToDTO(List<Book> listOfBooks) {
		List<BookDTO> listOfBookDTO = new ArrayList<BookDTO>();
		for (Book book : listOfBooks) {
			BookDTO bookDTO = convertToDTO(book);
			listOfBookDTO.add(bookDTO);
		}
		return listOfBookDTO;
	}

	/**
	 * Convert the book to bookDTO
	 * @param book
	 * @return
	 */
	private BookDTO convertToDTO(Book book) {
		if (book == null)
			return new BookDTO();
		Set<Author> listOfAuthors = book.getAuthors();
		Set<AuthorDTO> listOfAuthorDTO = new HashSet<AuthorDTO>();
		for (Author author : listOfAuthors) {
			listOfAuthorDTO.add(new AuthorDTO(author.getAuthorId(), author.getName()));
		}
		BookDTO bookDTO = new BookDTO(book.getBookId(), book.getCategory(), book.getTitle(), book.getLanguage(),
				book.getYear(), book.getPrice(), listOfAuthorDTO);
		return bookDTO;
	}
}
