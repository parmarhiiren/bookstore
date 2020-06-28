package com.example.bookstore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bookstore.domain.Book;
import com.example.bookstore.input.BookStore;
import com.example.bookstore.utility.Utility;

@SpringBootApplication
public class SpringBookstoreApplication  implements CommandLineRunner {

	@Autowired
	private Utility utility;

	public static void main(String[] args)  {
		SpringApplication.run(SpringBookstoreApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		BookStore bookStore = utility.convertXmlToObject();
		List<Book> listOfBooks = utility.convertBookStoreToDomainObjectsAndSave(bookStore);
    }
}
