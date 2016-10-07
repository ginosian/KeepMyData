package demo.dao;

import demo.dto.CatalogDTO;

import java.util.List;

/**
 * Created by Test on 9/16/2016.
 */
public interface CatalogDAO {

    CatalogDTO getCatalogById(Long id);
    CatalogDTO addCatalog (CatalogDTO catalog);
    List<CatalogDTO> getAllCatalogs ();
}
