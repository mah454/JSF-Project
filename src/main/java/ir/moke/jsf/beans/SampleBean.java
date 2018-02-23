package ir.moke.jsf.beans;

import ir.moke.jsf.model.entities.User;
import ir.moke.jsf.model.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SampleBean {

    @EJB
    private UserService userService ;


    public void updateUser() {
        User admin  = userService.find("admin") ;
        admin.setUsername("Sina");
        userService.modify(admin);
    }
}
