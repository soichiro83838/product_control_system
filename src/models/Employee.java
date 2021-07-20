package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "employees")
@NamedQueries({
    @NamedQuery(
        name = "getAllEmployees",
        query = "SELECT e FROM Employee AS e ORDER BY e.id DESC"
    ),
    @NamedQuery(
        name = "getEmployeesCount",
        query = "SELECT COUNT(e) FROM Employee AS e"
    ),
    @NamedQuery(
        name = "checkRegisteredEmail",
        query = "SELECT COUNT(e) FROM Employee AS e WHERE e.email = :email"
    ),
    @NamedQuery(
        name = "checkLoginCodeAndPassword",
        query = "SELECT e FROM Employee AS e WHERE e.email = :email AND e.password = :pass"
    )
})
@Entity
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "privilege", nullable = false)
    private Integer privilege;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAdmin_flag() {
        return privilege;
    }

    public void setAdmin_flag(Integer privilege) {
        this.privilege = privilege;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}