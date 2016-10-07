package demo.dao;


import demo.dto.UserDTO;

/**
 * Created by Test on 9/16/2016.
 */
public interface UserDAO {
    void createRememberMeTable();
    UserDTO getUserById(Long id);
    UserDTO addUser(UserDTO userDTO);
    UserDTO getUserByUsername(String login);
}
