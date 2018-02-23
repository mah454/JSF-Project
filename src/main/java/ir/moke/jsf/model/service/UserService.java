package ir.moke.jsf.model.service;

import ir.moke.jsf.model.entities.Role;
import ir.moke.jsf.model.entities.User;
import ir.moke.jsf.model.entities.UserRole;
import ir.moke.jsf.model.repository.impl.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class UserService implements GenericService<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.insert(user);
        LOGGER.debug("Add user : " + user.getUsername());
    }

    public void save(User user, List<Role> roles) {
        roleUnmarshaller(user, roles);
        try {
            userRepository.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.debug("Add user and roles : " + user.getUsername());
    }

    public void modify(User user, List<Role> roles) {
        roleUnmarshaller(user, roles);
        try {
            userRepository.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.debug("Update user and roles : " + user.getUsername());
    }

    private void roleUnmarshaller(User user, List<Role> roles) {
        user.getUserRoles().clear();
        for (Role r : roles) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(r);
            user.addUserRole(userRole);
        }
    }

    @Override
    public void modify(User user) {
        userRepository.update(user);
        LOGGER.debug("Modify user by object : " + user.getUsername());
    }

    @Override
    public void remove(long id) {
        userRepository.delete(id);
        LOGGER.debug("Remove user by id : " + id);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(find(user.getId()));
        LOGGER.debug("Remove user by object : " + user.getId());
    }

    @Override
    public boolean isExist(String username) {
        User user = find(username);
        return user != null;
    }

    @Override
    public boolean isExist(long id) {
        User user = find(id);
        return user != null;
    }

    @Override
    public User find(long id) {
        return userRepository.select(id);
    }

    @Override
    public User find(String username) {
        return userRepository.select(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.select();
    }

    public List<Role> findUserRoles(String username) {
        List<Role> roles = new ArrayList<>();
        User user = find(username);
        if (user != null) {
            List<UserRole> userRoles = user.getUserRoles();
            userRoles.forEach(e -> roles.add(e.getRole()));
        }
        LOGGER.info("Roles assigned to user : " + username + "is : " + roles.toString());
        return roles;
    }
}