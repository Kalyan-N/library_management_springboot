package com.project.librarymanagement.createRequest;

import com.project.librarymanagement.models.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentCreateRequest {
    @Positive
    private int age;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    private String mobile;

    public Student toStudent(){
        return Student.builder()
                .age(age)
                .name(name)
                .email(email)
                .mobile(mobile)
                .build();
    }
}
