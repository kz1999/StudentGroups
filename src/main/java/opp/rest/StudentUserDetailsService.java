package opp.rest;

import opp.domain.Student;
import opp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;
import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;

@Service
public class StudentUserDetailsService implements UserDetailsService {
    @Value("${opp.admin.password}")
    private String adminPasswordHash;

    @Autowired
    private StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, adminPasswordHash, authorities(username));
    }

    private List<GrantedAuthority> authorities(String username) {
        if ("admin".equals(username))
            return commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        Student student = studentService.findByJmbag(username);
        if (student == null) {
            throw new UsernameNotFoundException("No user " + username);
        }
        if (student.isLead())
            return commaSeparatedStringToAuthorityList("ROLE_LEAD");
        else
            return NO_AUTHORITIES;
    }
}
