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
@Setter
@Getter
@Builder

public class Book {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String name;
    private int cost;
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties(value = "bookList")
    private Author author;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @ManyToOne
    @JsonIgnoreProperties(value = "bookList")
    private Student student;
    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties(value = "bookList")
    private List<Transaction> transaction;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

}
