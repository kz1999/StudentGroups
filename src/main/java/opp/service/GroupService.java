package opp.service;

import opp.domain.Group;

import java.util.List;

public interface GroupService {
    List<Group> listAll();

    Group createGroup(String groupName, String coordinatorJmbag);
}
