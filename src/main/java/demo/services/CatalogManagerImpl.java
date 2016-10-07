package demo.services;

import demo.dao.CatalogDAO;
import demo.dto.CatalogDTO;
import demo.dto.UserDTO;
import demo.exceptions.NoSuchCatalogException;
import demo.exceptions.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Test on 9/16/2016.
 */

@Service
public class CatalogManagerImpl implements CatalogManager {

    @Autowired
    CatalogDAO catalogDAO;

    @Autowired
    UserManager userManager;

    @Override
    public CatalogDTO getCatalogById(Long id) {
        if(id == null) return null;
        return catalogDAO.getCatalogById(id);
    }

    @Override
    public CatalogDTO addCatalog(String username, String url, String description, byte [] image) {
        if(username.isEmpty() || url.isEmpty() || description.isEmpty() || image.length == 0 ) return null;
        UserDTO user = userManager.getUserByUsername(username);
        if(user == null) throw  new NoSuchUserException();
        CatalogDTO catalog = new CatalogDTO(user, url, description, image);
        catalogDAO.addCatalog(catalog);
        return catalog;
    }

    @Override
    public List<CatalogDTO> getAllCatalogs() throws Exception{
        List<CatalogDTO> catalogs = catalogDAO.getAllCatalogs();
        if (catalogs.size() == 0)throw new NoSuchCatalogException();
        return catalogs;
    }

}
