package ir.moke.jsf.model.entities;

import ir.moke.jsf.convert.DateConverter;
import org.apache.johnzon.mapper.JohnzonConverter;
import org.apache.johnzon.mapper.JohnzonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERS")
@SequenceGenerator(name = "DEFAULT_SEQ",sequenceName = "USERS_SEQ",allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends BaseEntity {

    @Basic
    @Column(name = "username", columnDefinition = "VARCHAR2(32)", unique = true)
    @XmlElement
    private String username;

    @Basic
    @Column(name = "password", columnDefinition = "VARCHAR2(32)")
    private String password;

    @Column(name = "active", nullable = false)
    @Type(type = "true_false")
    private boolean active;

    @Column(name = "expr_date")
    @Temporal(TemporalType.DATE)
    @JohnzonConverter(DateConverter.class)
    private Date expireDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = UserRole.class, orphanRemoval = true, fetch = FetchType.LAZY)
    @XmlTransient
    @JohnzonIgnore
    private List<UserRole> userRoles;

    public User() {
        userRoles = new ArrayList<>();
    }

    public User(long id, long version, String username, String password, boolean active, Date expireDate) {
        super(id, version);
        this.username = username;
        this.password = password;
        this.active = active;
        this.expireDate = expireDate;
        userRoles = new ArrayList<>();
    }

    public User(String username, String password, boolean active, Date expireDate) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.expireDate = expireDate;
        userRoles = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    @JohnzonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "version='" + getVersion() + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", expireDate=" + expireDate +
                '}';
    }

    public void addUserRole(UserRole userRole) {
        userRoles.add(userRole);
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        userRoles.forEach(e -> roles.add(e.getRole()));
        return roles;
    }

    @Override
    public boolean equals(Object otherUser) {
        if (otherUser == null) return false;
        if (this == otherUser) return true;
        if (!(otherUser instanceof User)) return false;
        User user = (User) otherUser;
        System.out.println(user.getUsername());
        System.out.println(((User) otherUser).getUsername());
        return this.username.equals(user.username) && this.expireDate.equals(user.expireDate);
    }
}
