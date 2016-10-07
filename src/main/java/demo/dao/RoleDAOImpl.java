package demo.dao;

import demo.dto.RoleDTO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Test on 9/16/2016.
 */
@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        Session session = getSession();
        try {
            session.save(roleDTO);
            return roleDTO;
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RoleDTO getRole(RoleDTO roleDTO) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from RoleDTO role where role.role = :role");
            String role = roleDTO.getRole();
            query.setParameter("role", role);
            List<RoleDTO> roleDTOs = query.list();
            if(roleDTOs.size() == 0) return null;
            return roleDTOs.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
