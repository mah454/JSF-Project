package ir.moke.jsf.model.service;

import ir.moke.jsf.model.entities.Role;
import ir.moke.jsf.model.repository.impl.RoleRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class RoleService implements GenericService<Role> {

    @EJB
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        try {
            roleRepository.insert(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Role role) {
        roleRepository.update(role);
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void remove(Role role) {

    }

    @Override
    public boolean isExist(String roleName) {
        Role role = find(roleName);
        return role != null;
    }

    @Override
    public boolean isExist(long id) {
        Role role = find(id) ;
        return role != null;
    }

    @Override
    public Role find(long id) {
        return roleRepository.select(id);
    }

    @Override
    public Role find(String str) {
        return roleRepository.select(str);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.select();
    }
}
