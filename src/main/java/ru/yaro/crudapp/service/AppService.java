package ru.yaro.crudapp.service;

import ru.yaro.crudapp.models.Role;
import ru.yaro.crudapp.models.User;

import java.util.List;
import java.util.Set;

public interface AppService {
    User getUserById(Long id);

    List<User> getAllUsers();

    Set<Role> getAllRoles();

    void addRole(Role role);

    Role getRoleByName(String name);

    void addUser(User user);

    void addAllRoles(Set<Role> roles);

    void setExistingRoles(User user);

    void fillRoles(String... roleNames);

    void deleteUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);
}
