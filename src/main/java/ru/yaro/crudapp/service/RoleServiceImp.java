package ru.yaro.crudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaro.crudapp.dao.RoleDao;
import ru.yaro.crudapp.models.Role;
import ru.yaro.crudapp.models.User;

import java.util.HashSet;
import java.util.Set;
@Service
public class RoleServiceImp implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImp() {
    }

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void setExistingRoles(User user) {
        Set<Role> roles = new HashSet<>(user.getRoles());
        user.removeRoles();
        for (Role role : roles) {
            user.addRole(getRoleByName(role.getName()));
        }

    }

    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAll();
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getByName(name);
    }

    @Override
    public void addAllRoles(Set<Role> roles) {
        roleDao.addAllRoles(roles);
    }

    @Override
    public void fillRoles(String... roleNames) {
        for (String roleName : roleNames) {
            roleDao.addRole(new Role(roleName));
        }
    }
}
