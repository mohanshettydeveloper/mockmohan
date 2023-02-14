package com.example.mockmohan.controller;

import com.example.mockmohan.model.Book;
import com.example.mockmohan.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/add")
    public Book createBook(@RequestBody @Validated Book book) {
        return bookRepository.save(book);
    }

}
