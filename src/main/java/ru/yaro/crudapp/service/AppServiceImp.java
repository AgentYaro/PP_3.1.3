package ru.yaro.crudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yaro.crudapp.config.UserPasswordEncoder;
import ru.yaro.crudapp.dao.RoleDao;
import ru.yaro.crudapp.dao.UserDao;
import ru.yaro.crudapp.models.Role;
import ru.yaro.crudapp.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class AppServiceImp implements AppService, UserDetailsService {
    private RoleDao roleDao;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public AppServiceImp() {
    }
@Autowired
    public AppServiceImp(RoleDao roleDao, UserDao userDao, UserPasswordEncoder userPasswordEncoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.passwordEncoder = userPasswordEncoder.getPasswordEncoder();
    }


    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
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
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    public void addAllRoles(Set<Role> roles) {
        roleDao.addAllRoles(roles);
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
    public void fillRoles(String... roleNames) {
        for (String roleName : roleNames) {
            roleDao.addRole(new Role(roleName));
        }
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userDao.getByEmail(username);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword().toLowerCase(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), getAuthorities(user.getRoles()));
    }
    private static List<GrantedAuthority> getAuthorities (Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
