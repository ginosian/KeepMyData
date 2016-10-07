package demo.dao;


import demo.dto.RoleDTO;

/**
 * Created by Test on 9/16/2016.
 */
public interface RoleDAO {
    RoleDTO addRole(RoleDTO roleDTO);
    RoleDTO getRole(RoleDTO roleDTO);
}
