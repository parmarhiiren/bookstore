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
public class AuthorController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	/**
	 * Get the the list of Authors
	 * @return
	 */
	@GetMapping(value = "/authors")
	public List<AuthorDTO> findAllAuthors() {
		List<Author> listOfAuthors = authorRepository.findAll();
		List<AuthorDTO> listOfAuthorDTO = convertToDTO(listOfAuthors);
		return listOfAuthorDTO;
	}

	/**
	 * Find author by Id
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/authors/{id}")
	public AuthorDTO findAuthorById(@PathVariable("id") Long id) {
		Optional<Author> author = authorRepository.findById(id);
		if (author.isPresent())
			return convertToDTO(author.get());
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not available for id " + id);
	}
	
	/**
	 * Add a author
	 * @param author
	 * @return
	 */
	@PostMapping(value = "/authors")
	public AuthorDTO createAuthor(@RequestBody Author author) {
		Author dbAuthor = authorRepository.findByName(author.getName());
		if(dbAuthor != null && dbAuthor.getAuthorId() != null) {
			return convertToDTO(dbAuthor);
		} else {
			Author authorReturned = authorRepository.save(author);
			return convertToDTO(authorReturned);
		}
	}
	
	/**
	 * update the author
	 * @param author
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/authors/{id}")
	public AuthorDTO updateAuthor(@RequestBody Author author, @PathVariable("id") Long id) {
		Optional<Author> dbAuthor = authorRepository.findById(id);
		if (dbAuthor.isPresent()) {
			Author existingAuthor = dbAuthor.get();
			existingAuthor.setName(author.getName());
			Author authorReturned = authorRepository.save(existingAuthor);
			return convertToDTO(authorReturned);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not available for id " + id);
		}
	}
	
	/**
	 * Delete the author
	 * @param id
	 */
	@DeleteMapping(value = "/authors/{id}")
	public void deleteAuthor(@PathVariable("id") Long id) {
		Optional<Author> dbAuthor = authorRepository.findById(id);
		if (dbAuthor.isPresent()) {
			Author existingAuthor = dbAuthor.get();
			authorRepository.deleteById(existingAuthor.getAuthorId());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not available for id " + id);
		}
	}

	/**
	 * convert authors to DTO
	 * @param listOfAuthors
	 * @return
	 */
	private List<AuthorDTO> convertToDTO(List<Author> listOfAuthors) {
		List<AuthorDTO> listOfAuthorDTO = new ArrayList<AuthorDTO>();
		for (Author author : listOfAuthors) {
			AuthorDTO authorDTO = convertToDTO(author);
			listOfAuthorDTO.add(authorDTO);
		}
		return listOfAuthorDTO;
	}

	/**
	 * convert author to DTO
	 * @param author
	 * @return
	 */
	private AuthorDTO convertToDTO(Author author) {
		if (author == null)
			return new AuthorDTO();

		Set<Book> listOfBooks = author.getBooks();
		Set<BookDTO> listOfBookDTO = new HashSet<BookDTO>();
		for (Book book : listOfBooks) {
			listOfBookDTO.add(new BookDTO(book.getBookId(), book.getCategory(), book.getTitle(), book.getLanguage(),
					book.getYear(), book.getPrice()));
		}
		AuthorDTO authorDTO = new AuthorDTO(author.getAuthorId(), author.getName(), listOfBookDTO);
		return authorDTO;
	}

}
