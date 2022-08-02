package opp.dao;

import opp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    int countByJmbag(String jmbag);

    Student findByJmbag(String jmbag);
}
