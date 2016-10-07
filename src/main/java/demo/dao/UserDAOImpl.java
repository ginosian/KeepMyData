package demo.dao;

import demo.dto.UserDTO;
import demo.exceptions.NoSuchUserException;
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
public class UserDAOImpl implements UserDAO {
   @Autowired
   SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void createRememberMeTable() {
        String remTableQuery = "CREATE TABLE IF NOT EXISTS persistent_logins ( " +
                "username VARCHAR(64) NOT NULL, " +
                "series VARCHAR(64) NOT NULL, " +
                "token VARCHAR(64) NOT NULL, " +
                "last_used TIMESTAMP NOT NULL, " +
                "PRIMARY KEY (series))";
        Session session = getSession();
        try {
            session.createSQLQuery(remTableQuery).executeUpdate();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from UserDTO user where user.id = :id");
            query.setParameter("id", id);
            List<UserDTO> userDTOList = query.list();
            if (userDTOList.size() == 0)throw new NoSuchUserException();
            return userDTOList.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        Session session = getSession();
        try{
            session.save(userDTO);
            return userDTO;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from UserDTO user where user.username = :username");
            query.setParameter("username", username);
            List<UserDTO> userDTOList = query.list();
            if (userDTOList.size() == 0) return null;
            return userDTOList.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
