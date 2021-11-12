package com.example.oauth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oauth.domain.bo.Book;
import com.example.oauth.domain.repository.BookRepository;
import com.example.oauth.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book findBookById(Long id) {
		return this.bookRepository.findBySelfId(id);
	}
	
	@Override
	public List<Book> findBookByTitle(String title) {
		return this.bookRepository.findBookByTitle(title);
	}
	
	@Transactional
	@Override
	public Book saveBook(Book book) {
		return this.bookRepository.save(book);
	}
	
}
