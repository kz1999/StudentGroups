package opp.rest;

import opp.domain.Student;
import opp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> listStudents() {
        return studentService.listAll();
    }

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
}
