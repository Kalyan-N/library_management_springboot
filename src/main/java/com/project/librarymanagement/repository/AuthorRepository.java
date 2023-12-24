package com.project.librarymanagement.repository;

import com.project.librarymanagement.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query(value = "select *from author where email=:email",nativeQuery = true)
    Author findAuthor(String email);
//    @Query(value = "select a from Author a where a.email=:email")
//    Author findAuthor(String email);
}
