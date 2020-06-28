package com.example.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByName(String name);

}
