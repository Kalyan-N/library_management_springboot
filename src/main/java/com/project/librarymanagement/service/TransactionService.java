package com.project.librarymanagement.service;

import com.project.librarymanagement.models.*;
import com.project.librarymanagement.repository.TransactionRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
//@Builder
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired(required = true)
    StudentService studentService;
    @Autowired(required = true)
    BookService bookService;
    @Value("${student.book.limit}")
    int studentBookLimit;
    @Value("${book.return.days}")
    int bookReturnDays;
    @Value("${book.fine.day}")
    int fineDay;


    public String issueBook(int bookId,int studentId) throws Exception {
        Student student=studentService.getStudentById(studentId);
        if (student==null){
            throw new Exception("Student is invalid,can't issue the book");
        }
        if (student.getBookList().size()>=studentBookLimit){
            throw new Exception("student book limit exceeded, can't issue the book");
        }
        Book book=bookService.getBookById(bookId);
        if (book==null){
            throw new Exception("book is not  available, can't issue the book");
        }
        if(book.getStudent()!=null){
            throw new Exception("book is already issued,can't issue the book");
        }
        Transaction transaction=Transaction.builder()
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.PENDING)
                .transactionId(UUID.randomUUID().toString())
                .build();
        transactionRepository.save(transaction);
        try {
            book.setStudent(student);
            bookService.createBook(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transaction);
        }
        catch (Exception exception){
            book.setStudent(null);
             bookService.createBook(book);
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transactionRepository.save(transaction);
        }
        return transaction.getTransactionId()+"issue method";
    }

    public String returnBook(int bookId,int studentId) throws Exception {
        Student student=studentService.getStudentById(studentId);
//        System.out.println(studentId);
        Book book=bookService.getBookById(bookId);
        if (book==null||student==null||book.getStudent()==null||book.getStudent().getStudentId()!=studentId){
//            System.out.println("book id"+book.getStudent().getStudentId());
            throw new Exception("either book or student are not present or student is not correct person that the corresponding book is issued");
        }
        List<Transaction> issuedTxns=transactionRepository.findByStudentAndBookAndTransactionTypeOrderByIdDesc(student,book,TransactionType.ISSUE);
        System.out.println(issuedTxns);
        Transaction issuedTxn=issuedTxns.get(0);
        long issuedTimeInMs=issuedTxn.getUpdatedOn().getTime();
        long currentTimeMs=System.currentTimeMillis();
        long timeDifference=currentTimeMs-issuedTimeInMs;
        long timeDiffInDays= TimeUnit.DAYS.convert(timeDifference,TimeUnit.MILLISECONDS);
        int fine=0;
        if(timeDiffInDays>bookReturnDays){
            fine= (int) ((timeDiffInDays-bookReturnDays)*fineDay);
        }
        Transaction transaction=Transaction.builder()
                .book(book)
                .student(student)
                .fine(fine)
                .transactionType(TransactionType.RETURN)
                .transactionStatus(TransactionStatus.PENDING)
                .transactionId(UUID.randomUUID().toString())
                .build();
        transactionRepository.save(transaction);
        try {
            book.setStudent(null);
            bookService.createBook(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transaction);
        }
        catch (Exception exception){

            bookService.createBook(book);
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
        }
        return transaction.getTransactionId();
    }
}
