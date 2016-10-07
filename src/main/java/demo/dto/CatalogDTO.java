package demo.dto;

import javax.persistence.*;

/**
 * Created by Test on 9/16/2016.
 */
@Entity
@Table(name = "catalog")
public class CatalogDTO {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserDTO userDTO;

    private String url;

    private String description;

    @Lob
    private byte [] image;

    public CatalogDTO() {
    }

    public CatalogDTO(UserDTO userDTO, String url, String description, byte[] image) {
        this.userDTO = userDTO;
        this.url = url;
        this.description = description;
        this.image = image;
    }

    public void setData(UserDTO userDTO, String url, String description, byte[] image) {
        this.userDTO = userDTO;
        this.url = url;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
