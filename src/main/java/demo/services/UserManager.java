package demo.services;


import demo.dto.RoleDTO;
import demo.dto.UserDTO;

/**
 * Created by Test on 9/16/2016.
 */
public interface UserManager {
    UserDTO getUserById(String id);
    UserDTO addUser(String name, String username, String password, boolean enabled, String role);
    UserDTO getUserByUsername(String login);
    RoleDTO getRoleByName(String roleName);
    void initDefaults();

}
