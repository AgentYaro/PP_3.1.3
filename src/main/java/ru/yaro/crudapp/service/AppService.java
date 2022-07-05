package ru.yaro.crudapp.service;

import ru.yaro.crudapp.models.Role;
import ru.yaro.crudapp.models.User;

public interface Service {
    Object getById(Long id);

    Object getAllUsers();

    Object getAllRoles();

    void addRole(Role admin);

    Role getRoleByName(String admin);

    void addUser(User yaro);
}
