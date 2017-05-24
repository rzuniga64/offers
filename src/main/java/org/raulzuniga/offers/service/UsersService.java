package org.raulzuniga.offers.service;


import org.raulzuniga.offers.dao.UsersDAO;
import org.raulzuniga.offers.models.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  OffersService class. A class that provides the service of coordinating data
 *  access objects.
 *
 *  @ Service annotation is equivalent to @Component.
 *  We need an XML file to load our service, service-context.xml.
 *  Autowiring our OffersDAO into OffersService. Then autowire OffersService
 *  into OfferController in the OfferController class.
 */
@Service("usersService")
public class UsersService {

    /** Offers data access object. */
    private UsersDAO usersDao;

    /**
     *  set the Users data access object.
     *  @param newUsersDao offersDao
     */
    @Resource
    public void setUsersDao(final UsersDAO newUsersDao) {
        this.usersDao = newUsersDao;
    }

    /**
     * Create a user.
     * @param user user
     */
    public void create(final User user) {

        usersDao.create(user);
    }

    /**
     * Username exists?
     * @param username username
     * @return true if exists; false otherwise
     */
    public boolean exists(final String username) {
        return usersDao.exists(username);
    }

    /**
     * getAllUsers method.
     * @return all users
     */
    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {

        return usersDao.getAllUsers();
    }
}
