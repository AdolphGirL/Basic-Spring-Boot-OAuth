package com.example.oauth.service;

import java.util.List;

import com.example.oauth.domain.bo.Book;

public interface BookService {
	
	public Book findBookById(Long id);
	
	public List<Book> findBookByTitle(String title);
	
	public Book saveBook(Book book);
	
}
