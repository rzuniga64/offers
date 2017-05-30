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
public class OffersDaoTests {

	@Autowired
	private OffersDAO offersDao;

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
	public void createOffer() {

        User user = new User("rzuniga64", "aggies92", "Raul Zuniga", "rzuniga64@gmail.com", true, "ROLE_ADMIN");

        assertTrue("User creation should return true", usersDao.create(user));

        Offer offer = new Offer(user, "This is a test offer.");

        assertTrue("Offer creation should return true", offersDao.create(offer));
    }

    @Test
    public void getOffers() {

        User user = new User("rzuniga64", "aggies92", "Raul Zuniga", "rzuniga64@gmail.com", true, "ROLE_ADMIN");

        assertTrue("User creation should return true", usersDao.create(user));

        Offer offer = new Offer(user, "This is a test offer.");

        assertTrue("Offer creation should return true", offersDao.create(offer));

        List<Offer> offers = offersDao.getOffers();

        assertEquals("Should be one offer in database.", 1, offers.size());

        assertEquals("Retrieved offer should match created offer.", offer, offers.get(0));
    }

    @Test
    public void getOffer() {

        User user = new User("rzuniga64", "aggies92", "Raul Zuniga", "rzuniga64@gmail.com", true, "ROLE_ADMIN");

        assertTrue("User creation should return true", usersDao.create(user));

        Offer offer = new Offer(user, "This is a test offer.");

        assertTrue("Offer creation should return true", offersDao.create(offer));

        List<Offer> offers = offersDao.getOffers();
        Offer retrievedOffer = offersDao.getOffer(offers.get(0).getId());

        assertEquals("Offer should match retrieved offer", offer, retrievedOffer);
    }

    @Test
    public void update() {

        User user = new User("rzuniga64", "aggies92", "Raul Zuniga", "rzuniga64@gmail.com", true, "ROLE_ADMIN");

        assertTrue("User creation should return true", usersDao.create(user));

        Offer offer = new Offer(user, "This is a test offer.");

        assertTrue("Offer creation should return true", offersDao.create(offer));

        List<Offer> offers = offersDao.getOffers();

        // Get the offer with ID filled in.
        offer = offers.get(0);

        offer.setText("Updated offer text.");
        assertTrue("Offer update should return true", offersDao.update(offer));
    }

    @Test
    public void delete() {

        User user = new User("rzuniga64", "aggies92", "Raul Zuniga", "rzuniga64@gmail.com", true, "ROLE_ADMIN");

        assertTrue("User creation should return true", usersDao.create(user));

        Offer offer = new Offer(user, "This is a test offer.");

        assertTrue("Offer creation should return true", offersDao.create(offer));

        // Get the offer with ID filled in.
        offer = offersDao.getOffers().get(0);

        offersDao.delete(offer.getId());

        List<Offer> empty = offersDao.getOffers();

        assertEquals("Offers lists should be empty.", 0, empty.size());
    }
}
