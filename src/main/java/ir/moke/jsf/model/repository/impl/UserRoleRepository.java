package ir.moke.jsf.model.repository.impl;

import ir.moke.jsf.model.entities.UserRole;
import ir.moke.jsf.model.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class UserRoleRepository extends Repository<UserRole> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleRepository.class);

    public UserRoleRepository() {
        super(UserRole.class);
    }

    public UserRole select(String username, String roleName) {
        UserRole userRole = null;

        try {
            userRole = (UserRole) entityManager.createQuery("SELECT e FROM UserRole e WHERE e.user.username=:x AND e.role.roleName=:y")
                    .setParameter("x", username)
                    .setParameter("y", roleName).getSingleResult();
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
        }

        return userRole;
    }
}
