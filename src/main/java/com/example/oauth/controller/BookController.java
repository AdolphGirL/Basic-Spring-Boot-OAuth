package com.example.oauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth.domain.bo.Book;
import com.example.oauth.domain.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping
	public Iterable<Book> findAll() {
		return this.bookRepository.findAll();
	}
	
//	@GetMapping("/title/{bookTitle}")
//	public List<?> findByTitle(@PathVariable String bookTitle){
//		return this.bookRepository.findByTitle(bookTitle);
//	}
	
	@GetMapping("/title/{bookTitle}")
	public List<Book> findByTitle(@PathVariable String bookTitle){
		return this.bookRepository.findBookByTitle(bookTitle);
	}
	
	@GetMapping("/{id}")
	public Book findOne(@PathVariable(value = "id") Long id) {
		return this.bookRepository.findById(id).orElseGet(Book::new);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable(value = "id") Long id) {
//		this.bookRepository.findById(id).orElseThrow(null);
		this.bookRepository.deleteById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book create(@RequestBody Book book) {
		return this.bookRepository.save(book);
	}
	
	@PutMapping("/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
		if (book.getId() != id){
//			throw new Exceptino
		}
		
		this.bookRepository.findById(id).orElseThrow(null);
		
		return this.bookRepository.save(book);
	}
	
}
