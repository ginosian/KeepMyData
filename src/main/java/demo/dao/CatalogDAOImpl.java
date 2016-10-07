package demo.dao;

import demo.dto.CatalogDTO;
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
public class CatalogDAOImpl implements CatalogDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }



    @Override
    public CatalogDTO getCatalogById(Long id) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from CatalogDAO catalog where catalog.id = :id");
            query.setParameter("id", id);
            List<CatalogDTO> catalogDTOList = query.list();
            if (catalogDTOList.size() == 0)throw new NoSuchUserException();
            return catalogDTOList.get(0);
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CatalogDTO addCatalog(CatalogDTO catalog) {
        Session session = getSession();
        try{
            session.save(catalog);
            return catalog;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CatalogDTO> getAllCatalogs() {
        Session session = getSession();
        try{
            Query query = session.createQuery("FROM CatalogDTO");
            List<CatalogDTO> catalogDTOList = query.list();
            return catalogDTOList;
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
