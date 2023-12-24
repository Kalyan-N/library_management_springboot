package com.project.librarymanagement.controller;

import com.project.librarymanagement.createRequest.BookCreateRequest;
import com.project.librarymanagement.models.Book;
import com.project.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookControllers {
    @Autowired
    BookService bookService;
    @PostMapping("/createBook")
    public Book createBook(@RequestBody BookCreateRequest bookCreateRequest){
        return bookService.createBook(bookCreateRequest.toBook());
    }
    @GetMapping("/allBooks")
    public List<Book> getAllBooks(){
            return bookService.getAllBooks();
    }
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId){
                return bookService.getBookById(bookId);
    }
}
