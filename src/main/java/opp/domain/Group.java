package opp.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity(name="SGROUP")
public class Group {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min=1, max=20)
    private String name;

    @OneToOne
    private Student coordinator;

    @OneToMany
    private Set<Student> members;

    public Group(String name, Student coordinator) {
        Assert.hasText(name, "Group name must have text");
        Assert.notNull(coordinator, "Coordinator must be set");
        this.name = name;
        this.coordinator = coordinator;
        this.members = new HashSet<>(Arrays.asList(coordinator));
    }

    public Group() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Student coordinator) {
        this.coordinator = coordinator;
    }

    public Set<Student> getMembers() {
        return members;
    }

    public void setMembers(Set<Student> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinator=" + coordinator +
                ", members=" + members +
                '}';
    }
}
