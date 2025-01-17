package com.example.SpringSecu.students;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentsController {

    private List<Students> students = new ArrayList<>(List.of(new Students(1, "femi", 90),
            new Students(2, "esther", 96))
        );


    @GetMapping("/students")
    public List<Students> getStudents() {
        return students;
    }
}
