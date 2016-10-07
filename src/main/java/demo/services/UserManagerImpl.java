package demo.services;

import demo.dao.RoleDAO;
import demo.dao.UserDAO;
import demo.dto.RoleDTO;
import demo.dto.UserDTO;
import demo.exceptions.EmptyRequiredValueException;
import demo.exceptions.NoSuchRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Test on 9/16/2016.
 */
@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    Environment environment;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static boolean defaultAreSet = false;

    @Override
    public void initDefaults() {
        if(defaultAreSet) return;

        userDAO.createRememberMeTable();

        String role_admin = environment.getProperty("role_user");

        RoleDTO adminRole = new RoleDTO(role_admin);
        RoleDTO adminRoleDTO = roleDAO.getRole(adminRole);


        if (adminRoleDTO == null){
            adminRoleDTO = new RoleDTO(adminRole.getRole());
            roleDAO.addRole(adminRoleDTO);
        }

        defaultAreSet = true;
    }

    @Override
    public UserDTO getUserById(String id) {
        if (id == null || id.isEmpty()) throw new EmptyRequiredValueException();
        return userDAO.getUserById(Long.parseLong(id));
    }

    @Override
    public UserDTO addUser(String name, String username, String password, boolean enabled, String role) {
        UserDTO user = userDAO.getUserByUsername(username);
        if(user != null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        Set<RoleDTO> roles = new HashSet<RoleDTO>();
        RoleDTO localRole = getRoleByName(role);
        if(localRole == null) throw new NoSuchRoleException();
        if(username == null || username.isEmpty() || password == null || password.isEmpty()
                || name == null || name.isEmpty()) throw new EmptyRequiredValueException();
        roles.add(localRole);
        userDTO.set(username, passwordEncoder.encode(password), name,
                true, roles);
        return userDAO.addUser(userDTO);
    }

    @Override
    public UserDTO getUserByUsername(String login) {
        if(login == null || login.isEmpty()) throw new EmptyRequiredValueException();
        return userDAO.getUserByUsername(login);
    }

    @Override
    public RoleDTO getRoleByName(String roleName) {
        if(roleName == null || roleName.isEmpty()) throw new NoSuchRoleException();
        RoleDTO role = new RoleDTO(roleName);
        return roleDAO.getRole(role);
    }

}
