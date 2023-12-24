package com.project.librarymanagement.controller;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.project.librarymanagement.createRequest.StudentCreateRequest;
import com.project.librarymanagement.models.Student;
import com.project.librarymanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
     StudentService studentService;
    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody StudentCreateRequest studentCreateRequest){

        return studentService.createStudent(studentCreateRequest.toStudent()) ;
    }

    @GetMapping("/allStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId ){
        return studentService.getStudentById(studentId);
    }
}
