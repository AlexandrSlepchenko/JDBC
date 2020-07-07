package hibernate.Lesson3.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {
    private Long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private List orders;

    public User() {
    }

    @Id
    @SequenceGenerator(name = "US_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "US_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Column(name = "USER_TYPE")
    public UserType getUserType() {
        return userType;
    }

    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, mappedBy = "userOrdered", fetch = FetchType.EAGER)
    public List getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }
}
