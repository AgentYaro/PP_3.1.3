package ru.yaro.crudapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.yaro.crudapp.dao.RoleDao;
import ru.yaro.crudapp.dao.UserDao;
import ru.yaro.crudapp.models.Role;
import ru.yaro.crudapp.models.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CrudAppApplicationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDao userDao;

    @Autowired private RoleDao roleDao;

    @Test
    public void testCreateRoles() {
    }
    @Test
    public void testCreateUser() {

    }
    @Test
    public void testAddRoleToNewUser() {
    }
    @Test
    public void testAddRoleToExistingUser() {
    }

}
