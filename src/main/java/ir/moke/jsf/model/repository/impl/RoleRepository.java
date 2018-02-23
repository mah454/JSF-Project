package ir.moke.jsf.model.repository.impl;

import ir.moke.jsf.model.entities.Role;
import ir.moke.jsf.model.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class RoleRepository extends Repository<Role> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    public RoleRepository() {
        super(Role.class);
    }

    public Role select(String roleName) {
        Role role = null;
        try {
            role = (Role) entityManager.createQuery("FROM Role u where u.roleName=:x").setParameter("x", roleName).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn(e.getMessage());
        }
        return role;
    }
}
