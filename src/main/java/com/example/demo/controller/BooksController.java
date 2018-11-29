package com.example.demo.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.collection.Books;
import com.example.demo.repository.BooksRepository;

@Controller
public class BooksController {
	@Autowired
	private BooksRepository brepo;
	
	@GetMapping("/books/{name}")
	public @ResponseBody Books getBooks(@PathVariable String name) {
		return brepo.findByName(name);
	}
	@PostMapping("/books")
	public @ResponseBody Books insertBooks(
			@RequestBody Books books
			) {
		return brepo.save(books);
	}
	@PutMapping("/books/{id}")
	public @ResponseBody Books updateBooks(
			@PathVariable("id") ObjectId id,
			@RequestBody Books books
			) {
		books.set_id(id);
		return brepo.save(books);
	}
	@DeleteMapping("/books/{id}")
	public @ResponseBody int deleteBooks(
			@PathVariable("id") ObjectId id,
			@RequestBody Books books
			) {
		books.set_id(id);
		brepo.delete(books);
		return brepo.findById(id.toString())==null?1:0;
	}

}
