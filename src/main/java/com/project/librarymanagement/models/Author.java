package com.project.librarymanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int authorId;
    @Column(length = 15)
    private String authorName;
    @Column(unique = true,nullable = false)
    private String email;
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties(value = {"author","createdOn","updatedOn"})
    private List<Book> bookList;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;
}
