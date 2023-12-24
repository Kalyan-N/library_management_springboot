package com.project.librarymanagement.service;

import com.project.librarymanagement.models.Author;
import com.project.librarymanagement.models.Book;
import com.project.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorService authorService;
    public Book createBook(Book book) {
        Author author=authorService.getorCreateAuthor(book.getAuthor());
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
}
