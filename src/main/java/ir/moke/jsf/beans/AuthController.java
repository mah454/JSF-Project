package ir.moke.jsf.beans;


import ir.moke.jsf.model.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@ManagedBean
@SessionScoped

//fixme : Fix Log IPV4 remote address
public class AuthController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private HttpServletRequest httpServletRequest;

    private User user;

    @PostConstruct
    private void init() {
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        user = new User();
    }

    public String login() {
        try {
            String remoteIpAddress = httpServletRequest.getRemoteAddr();
            httpServletRequest.login(user.getUsername(), user.getPassword());
            LOGGER.info("Login success : " + user.getUsername() + " " + remoteIpAddress);
            return "/panel/dashboard.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
        }
        return "/index.xhtml";
    }

    //TODO : Logout logging : Username + remoteIpAddress must logged
    public String logout() {
        String target = "/index.xhtml?faces-redirect=true";
        try {
            String username = httpServletRequest.getRemoteUser();
            httpServletRequest.logout();
            LOGGER.info("Logout success : " + username);
            return target;
        } catch (ServletException e) {
            LOGGER.warn(e.getMessage());
        }
        return target;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
