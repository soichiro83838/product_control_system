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

@Table(name = "parts")
//全てのpartを表示する
@NamedQueries({
    @NamedQuery(
        name = "getAllParts",
        query = "SELECT r FROM Part AS r ORDER BY r.id DESC"
    ),
    //全てのpartを数える
    @NamedQuery(
        name = "getPartsCount",
        query = "SELECT COUNT(r) FROM Part AS r"
    ),
    // あるuserが投稿したpostを表示する
    @NamedQuery(name = "getMyAllParts", query = "SELECT p FROM Part AS p WHERE p.user = :user ORDER BY p.id DESC"),
    // あるuserが投稿したpostの数を数える
    @NamedQuery(name = "getMyPartsCount", query = "SELECT COUNT(p) FROM Part AS p WHERE p.user = :user")

})
@Entity
public class Part {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "partname", length = 255, nullable = false)
    private String partname;

    @Lob
    @Column(name = "modelnumber", nullable = false)
    private String modelnumber;

    @Column(name = "manufacture", nullable = false)
    private String manufacture;

    @Column(name = "image", length = 255, nullable = false)
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getEmployee() {
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

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
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
}
