package opp.service.impl;

import opp.dao.GroupRepository;
import opp.domain.Group;
import opp.domain.Student;
import opp.service.GroupService;
import opp.service.RequestDeniedException;
import opp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class GroupServiceJpa implements GroupService{
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Group> listAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group createGroup(String groupName, String coordinatorJmbag) {
        Student coordinator = studentService.findByJmbag(coordinatorJmbag);
        if (coordinator == null) {
            throw new RequestDeniedException("No student with JMBAG" + coordinatorJmbag);
        }
        Group g = groupRepository.findByMember(coordinator);
        if (g != null) {
            throw new RequestDeniedException("Coordinator already member of " + g);
        }

        return groupRepository.save(new Group(groupName, coordinator));
    }
}
