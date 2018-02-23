package ir.moke.jsf.beans.users;

import ir.moke.jsf.beans.ActionModifier;
import ir.moke.jsf.model.entities.Role;
import ir.moke.jsf.model.entities.User;
import ir.moke.jsf.model.service.RoleService;
import ir.moke.jsf.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserManager implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);

    private User user;
    private List<User> userList;
    private List<Role> selectedRoles;
    private List<Role> currentRoles;

    @EJB
    private RoleService roleService;

    @EJB
    private UserService userService;

    @Inject
    private ActionModifier actionModifier;

    @PostConstruct
    private void init() {
        user = new User();
        currentRoles = roleService.findAll();
    }

    public void reset() {
        userList = userService.findAll();
        selectedRoles = new ArrayList<>();
        user = new User();
    }

    public void execute() {
        String action = actionModifier.getActionType();
        if (action.equals("Create")) {
            registerUser();
        } else if (action.equals("Delete")) {
            removeUser();
        } else if (action.equals("Update")) {
            updateUser();
        }
    }

    private void registerUser() {
        userService.save(user, selectedRoles);
        reset();
    }

    private void updateUser() {
        userService.modify(user, selectedRoles);
        reset();
    }

    public void removeUser() {
        userService.remove(user);
        reset();
    }

    public User getUser() {
        if (user != null)
            selectedRoles = user.getRoles();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        if (userList == null)
            userList = userService.findAll();
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Role> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<Role> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public List<Role> getCurrentRoles() {
        if (currentRoles == null)
            roleService.findAll();
        return currentRoles;
    }

    public void setCurrentRoles(List<Role> currentRoles) {
        this.currentRoles = currentRoles;
    }
}