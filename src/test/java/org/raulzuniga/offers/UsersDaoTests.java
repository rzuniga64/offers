package org.raulzuniga.offers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.raulzuniga.offers.dao.OffersDAO;
import org.raulzuniga.offers.dao.UsersDAO;
import org.raulzuniga.offers.models.Offer;
import org.raulzuniga.offers.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersDaoTests {

	@Autowired
	private UsersDAO usersDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreate() {

		User user = new User("rzuniga64", "aggies92", "rzuniga64@gmail.com", "Raul Zuniga", true, "ROLE_ADMIN");

		assertTrue("User creation should return true", usersDao.create(user));

	}

    @Test
    public void testGetAllUsers() {

        User user = new User("rzuniga64", "aggies92", "rzuniga64@gmail.com", "Raul Zuniga", true, "ROLE_ADMIN");

        assertTrue("User creation should return true", usersDao.create(user));

        List<User> users = usersDao.getAllUsers();

        assertEquals("Number of users should be 1.", 1, users.size());
    }

    @Test
    public void testExists() {

        User user = new User("rzuniga64", "aggies92", "rzuniga64@gmail.com", "Raul Zuniga", true, "ROLE_ADMIN");

        assertTrue("User creation should return true", usersDao.create(user));

        assertTrue("User should exist.", usersDao.exists(user.getUsername()));

        List<User> users = usersDao.getAllUsers();

        assertEquals("Created user should be identical to retrieved user", user, users.get(0));
    }
}
