package ir.moke.jsf.model.entities;

import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLES")
@SequenceGenerator(name = "DEFAULT_SEQ", sequenceName = "ROLES_SEQ", allocationSize = 1)
@XmlRootElement
public class Role extends BaseEntity {

    @Basic
    @Column(name = "role_name", columnDefinition = "VARCHAR2(32)", unique = true)
    @XmlElement
    private String roleName;

    @Basic
    @Column(name = "description", columnDefinition = "VARCHAR2(256)")
    @XmlElement
    private String description;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "role", targetEntity = UserRole.class, fetch = FetchType.LAZY)
    @XmlTransient
    @JohnzonIgnore
    private List<UserRole> userRoles;

    public Role() {
        userRoles = new ArrayList<>();
    }

    public Role(long id, long version, String roleName, String description) {
        super(id, version);
        this.roleName = roleName;
        this.description = description;
        userRoles = new ArrayList<>();
    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
        userRoles = new ArrayList<>();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object otherRole) {
        if (otherRole == null) return false;
        if (this == otherRole) return true;
        if (!(otherRole instanceof Role)) return false;
        Role role = (Role) otherRole;
        return this.roleName.equals(role.roleName);
    }
}
