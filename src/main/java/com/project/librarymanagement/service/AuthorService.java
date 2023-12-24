package com.project.librarymanagement.service;

import com.project.librarymanagement.models.Author;
import com.project.librarymanagement.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public Author getorCreateAuthor(Author author) {
        Author authorFromDb=authorRepository.findAuthor(author.getEmail());
        if(authorFromDb==null){
            authorFromDb= authorRepository.save(author);
        }
        return authorFromDb;
    }
}
