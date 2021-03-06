package org.raulzuniga.offers.service;

import org.raulzuniga.offers.dao.OffersDAO;
import org.raulzuniga.offers.models.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

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
@Service("offersService")
public class OffersService {

    /** Offers data access object. */
    private OffersDAO offersDao;

    /**
     *  set the Offers data access object.
     *  Inject the OffersDao class.
     *  @param newOffersDao offersDao
     */
    @Autowired
    public void setOffersDao(final OffersDAO newOffersDao) {

        this.offersDao = newOffersDao;
    }

    /**
     *  Get the current list of Offers.
     *  @return the current list of Offers.
     */
    public List<Offer> getCurrent() {

        return offersDao.getOffers();
    }

    /**
     * Create an offer.
     * @param offer offer
     */
    @Secured({"ROLE_USER, ROLE_ADMIN" })
    public void create(final Offer offer) {

        offersDao.create(offer);
    }

    /**
     * get offer.
     * @param id id
     */
    public Offer getOffer(final int id) {

        return offersDao.getOffer(id);
    }
}
