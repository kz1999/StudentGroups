package opp.service;

import opp.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAll();

    Student createStudent(Student student);

    Student findByJmbag(String jmbag);
}
