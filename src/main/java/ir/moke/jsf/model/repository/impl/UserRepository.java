package ir.moke.jsf.model.repository.impl;

import ir.moke.jsf.model.entities.User;
import ir.moke.jsf.model.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class UserRepository extends Repository<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository() {
        super(User.class);
    }

    public User select(String username) {
        User user = null;
        try {
            user = (User) entityManager.createQuery("SELECT u FROM User u where u.username=:x").setParameter("x", username).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.warn(e.getMessage());
        }
        return user;
    }
}
