package demo.services;

import demo.dto.CatalogDTO;

import java.util.List;

/**
 * Created by Test on 9/16/2016.
 */
public interface CatalogManager {

    CatalogDTO getCatalogById(Long id);
    CatalogDTO addCatalog (String username, String url, String description, byte [] image);
    List<CatalogDTO> getAllCatalogs () throws Exception;
}
