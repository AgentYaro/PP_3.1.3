package ru.yaro.crudapp.dao;

import ru.yaro.crudapp.models.Role;

import java.util.Set;

public interface RoleDao {
    Role getByName(String name);

    void addRole(Role role);

    Set<Role> getAll();

    void addAllRoles(Set<Role> roles);
}
