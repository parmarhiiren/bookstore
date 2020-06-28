package com.example.bookstore.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.bookstore.domain.Author;
import com.example.bookstore.domain.Book;
import com.example.bookstore.input.BookCriteria;
import com.example.bookstore.input.BookStore;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;

@Service
@Transactional
public class Utility {

	private final Logger logger = LoggerFactory.getLogger(Utility.class);
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	/**
	 * Covert the xml which is present in src/main/resources to BookStore Object
	 * 
	 * @return
	 */
	public BookStore convertXmlToObject() {

		BookStore bookStore = null;
		try {

			// create an instance of 'JAXBContext'
			JAXBContext context = JAXBContext.newInstance(BookStore.class);

			// create an instance of 'Unmarshaller'
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// XML file path
			File file = ResourceUtils.getFile("classpath:bookstore.xml");

			// convert XML file to 'BookStore' object
			bookStore = (BookStore) unmarshaller.unmarshal(file);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return bookStore;
	}

	/**
     * Convert the Book store to domain Object and store it
     * @param bookstore
     * @return
     */
    public List<Book> convertBookStoreToDomainObjectsAndSave(BookStore bookstore) {
    	List<Book> listOfBooks = new ArrayList<Book>();
    	List<BookCriteria> listOfBookCriteria = bookstore.getBooks();
    	
    	for(BookCriteria bookCriteria : listOfBookCriteria) {
    		// Convert list of Author Criteria (List of Author strings) to List of Author Objects
    		Set<Author> listOfAuthors = convertAuthorCriteriaToAuthor(bookCriteria.getAuthors().getAuthor());
    		// Create a domain book object
    		Book book = new Book(	bookCriteria.getCategory(), 
    								bookCriteria.getTitle().getValue(), 
    								bookCriteria.getTitle().getLang(), 
    								bookCriteria.getYear(), 
    								bookCriteria.getPrice(),
    								listOfAuthors
    							);
        	
    		Book bookReturned = bookRepository.save(book);
        	listOfBooks.add(bookReturned);
    	}

    	return listOfBooks;
    }

    /**
     * Convert List of AuthorCriteria to List<Author>
     * 
     * @param listOfAuthorCriteria
     * @return
     */
	private Set<Author> convertAuthorCriteriaToAuthor(List<String> listOfAuthorCriteria) {
		Set<Author> listOfAuthors = new HashSet<Author>();
		for(String authorCriteria : listOfAuthorCriteria) {
			Author existingAuthor = authorRepository.findByName(authorCriteria);
			if(existingAuthor != null && existingAuthor.getAuthorId() != null) {
				listOfAuthors.add(existingAuthor);
			} else {
				Author author = new Author(authorCriteria);
				listOfAuthors.add(author);
			}
		}
		return listOfAuthors;
	}

}