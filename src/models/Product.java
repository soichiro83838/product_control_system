package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "products")
@NamedQueries({
    @NamedQuery(
            name = "getAllProducts",
            query = "SELECT r FROM Product AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getProductsCount",
            query = "SELECT COUNT(r) FROM Product AS r"
            ),
    @NamedQuery(
            name = "getMyAllProducts",
            query = "SELECT p FROM Product AS p WHERE p.user = :user ORDER BY p.id DESC"
            ),
    @NamedQuery(
            name = "getMyProductsCount",
            query = "SELECT COUNT(p) FROM Product AS p WHERE p.user = :user"
            )
})
@Entity
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "productname", length = 255, nullable = false)
    private String productname;

    @Lob
    @Column(name = "modelnumber", nullable = false)
    private String modelnumber;

    @Column(name = "manufacture", nullable = false)
    private String manufacture;

    @Column(name = "image", length = 255, nullable = false)
    private String image;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getModelnumber() {
        return modelnumber;
    }

    public void setModelnumber(String modelnumber) {
        this.modelnumber = modelnumber;
    }
    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
