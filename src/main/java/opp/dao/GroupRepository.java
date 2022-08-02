package opp.dao;

import opp.domain.Group;
import opp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT g FROM SGROUP g WHERE :s MEMBER OF g.members")
    Group findByMember(@Param("s") Student student);
}
