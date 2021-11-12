package com.example.oauth.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.oauth.domain.bo.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	public List<Book> findByTitle(String title);
	
	@Query("select a from Book a where a.id = :id ")
	public Book findBySelfId(@Param(value = "id")Long id);
	
	@Query("select b from Book b where b.title = LOWER(:title)")
	public List<Book> findBookByTitle(@Param(value = "title")String title);
	
}
