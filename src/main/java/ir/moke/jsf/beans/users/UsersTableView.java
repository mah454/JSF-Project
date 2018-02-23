package ir.moke.jsf.beans.users;


import ir.moke.jsf.model.entities.User;
import ir.moke.jsf.model.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UsersTableView {

    private List<User> userList;
    @EJB
    private UserService userService;

    @PostConstruct
    private void init() {
        userList = userService.findAll();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
