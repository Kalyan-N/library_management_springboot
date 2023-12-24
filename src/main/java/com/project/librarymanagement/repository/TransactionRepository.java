package com.project.librarymanagement.repository;

import com.project.librarymanagement.models.Book;
import com.project.librarymanagement.models.Student;
import com.project.librarymanagement.models.Transaction;
import com.project.librarymanagement.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
//    public Transaction getTransactionById(int transactionId);
//    public List<Transaction> findByStudent(int studentId);
//
//    public List<Transaction> findByStudentAndBook(Student student, Book book);
//    List<Transaction> findByStudentOrderByTransactionId(Student student);

    public List<Transaction> findByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType type);

}
