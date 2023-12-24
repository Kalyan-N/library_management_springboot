package com.project.librarymanagement.createRequest;

import com.project.librarymanagement.models.Author;
import com.project.librarymanagement.models.Book;
import com.project.librarymanagement.models.Genre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private Genre genre;
    @NotBlank
    private int cost;
    @NotBlank
    private String authorName;
    @NotBlank
    @Email
    private String email;

    public Book toBook(){
        Author author=Author.builder()
                .authorName(authorName)
                .email(email)
                .build();
        return Book.builder()
                .name(name)
                .genre(genre)
                .cost(cost)
                .author(author)
                .build();
    }
}
