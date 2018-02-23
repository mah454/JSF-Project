package ir.moke.jsf.model.service;


import ir.moke.jsf.model.entities.UserRole;
import ir.moke.jsf.model.repository.impl.UserRoleRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class UserRoleService implements GenericService<UserRole> {

    @EJB
    private UserRoleRepository userRoleRepository;

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.insert(userRole);
    }

    @Override
    public void modify(UserRole userRole) {
        userRoleRepository.update(userRole);
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void remove(UserRole userRole) {

    }

    @Override
    public boolean isExist(String str) {
        //TODO : Not implemented yet .
        return false;
    }

    @Override
    public boolean isExist(long id) {
        UserRole userRole = userRoleRepository.select(id);
        return userRole != null;
    }

    public boolean isExist(String username, String roleName) {
        return find(username, roleName) != null;
    }

    @Override
    public UserRole find(long id) {
        return userRoleRepository.select(id);
    }

    @Override
    public UserRole find(String str) {
        //TODO: Not implemented yet .
        return null;
    }

    public UserRole find(String username, String roleName) {
        return userRoleRepository.select(username, roleName);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.select();
    }
}
