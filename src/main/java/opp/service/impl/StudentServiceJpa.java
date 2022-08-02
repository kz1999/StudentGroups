package opp.service.impl;

import opp.dao.StudentRepository;
import opp.domain.Student;
import opp.service.RequestDeniedException;
import opp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class StudentServiceJpa implements StudentService {
    @Autowired
    private StudentRepository studentRepo;

    private static final String JMBAG_FORMAT = "[0-9]{10}";

    @Override
    public List<Student> listAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        Assert.notNull(student, "Student object must be given");
        Assert.isNull(student.getId(), "" +
                "Student ID must be null, not " + student.getId());
        String jmbag = student.getJmbag();
        Assert.hasText(jmbag, "JMBAG must be given");
        Assert.isTrue(jmbag.matches(JMBAG_FORMAT),
                "JMBAG must have 10 digits");
        if (studentRepo.countByJmbag(student.getJmbag()) > 0)
            throw new RequestDeniedException(
                    "Student with JMBAG " + student.getJmbag() + " already exists"
            );
        return studentRepo.save(student);
    }

    public Student findByJmbag(String jmbag) {
        Assert.notNull(jmbag, "JMBAG must be given");
        return studentRepo.findByJmbag(jmbag);
    }
}
