package demo.services;

import demo.dao.UserDAO;
import demo.dto.RoleDTO;
import demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Test on 9/16/2016.
 */

@Service("customUsrService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user = userDAO.getUserByUsername(s);
        List<GrantedAuthority> rolesList = buildUserRoleList(user.getUserRoles());
        return buildUserForAuthentication(user, rolesList);
    }

    private List<GrantedAuthority> buildUserRoleList(Set<RoleDTO> userRoles){
        Set<GrantedAuthority> tempUserRoles = new HashSet<GrantedAuthority>();
        for (RoleDTO userRole : userRoles){
            tempUserRoles.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
        }
        return new ArrayList<GrantedAuthority>(tempUserRoles);
    }

    private User buildUserForAuthentication(UserDTO user, List<GrantedAuthority> userRolesList){
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, userRolesList);
    }
}
