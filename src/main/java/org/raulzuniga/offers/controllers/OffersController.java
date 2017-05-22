package org.raulzuniga.offers.controllers;

import org.raulzuniga.offers.models.Offer;
import org.raulzuniga.offers.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 *  OffersController class.
 *  Autowiring our OffersDAO into OffersService.
 */
@Controller
public class OffersController {

    /** Offers service. */
    private OffersService offersService;

    /** Default constructor. */
    public OffersController() {
        System.out.println("Succesfully loaded offers controller");
    }

    /**
     * Set the Offers service.
     * Inject the OffersService class
     * @param newOffersService newOffersService
     */
    @Autowired
    public void setOffersService(final OffersService newOffersService) {

        System.out.println("In setOffersController");
        this.offersService = newOffersService;
    }

    /*
    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(DataAccessException ex) {
        return "error";
    }*/

    /**
     *  showHome method.
     * '/' means if we go to the root of our application then it will be
     *  capable of handling it.
     *  @param model model
     *  @return a String
     */
    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public String showOffers(final Model model) {

        List<Offer> offers = offersService.getCurrent();
        model.addAttribute("offersList", offers);
        return "offers";
    }

    /**
     *  showTest method.
     *  @param model model
     *  @param id id
     *  @return a String
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTest(final Model model,
                           @RequestParam("id") final String id) {

        System.out.println("Id is: " + id);
        return "offers";
    }

    /**
     *  createOffer method.
     *  @param model model
     *  @return the offers page as a String
     */
    @RequestMapping("/createoffer")
    public String createOffer(final Model model) {

        System.out.println("In createOffers");
        model.addAttribute("offer", new Offer());

        return "createoffer";
    }

    /**
     * doCreate method.
     * @param model the model
     * @param offer the offer
     * @param result result
     * @return the web page as a String
     */
    @RequestMapping(value = "/docreate", method = RequestMethod.POST)
    public String doCreate(final Model model,
                           @Valid final Offer offer,
                           final BindingResult result) {

        if (result.hasErrors()) {
            return "createoffer";
        }

        offersService.create(offer);

        return "offercreated";
    }

    /*@RequestMapping("/")
    public String showHome(final Model model) {
        model.addAttribute("name", "Tiffany");
        return "index";
    }*/

    /*@RequestMapping("/")
    public String showHome(final HttpSession session) {
        session.setAttribute("name", "Boris");
        return "index";
    }*/
}
