package opp.rest;

import opp.domain.Group;
import opp.service.GroupService;
import opp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Group> listGroups() {
        return groupService.listAll();
    }

    @PostMapping
    @Secured("ROLE_LEAD")
    public Group createGroup(
            @RequestBody CreateGroupDTO dto,
            @AuthenticationPrincipal User u) {
        return groupService.createGroup(dto.getName(), u.getUsername());
    }
}
