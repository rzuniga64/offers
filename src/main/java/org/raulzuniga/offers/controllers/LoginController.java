package org.raulzuniga.offers.controllers;


import org.raulzuniga.offers.models.Authority;
import org.raulzuniga.offers.models.User;
import org.raulzuniga.offers.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/** LoginController class. */
@Controller
public class LoginController {

    /** Users service. */
    private UsersService usersService;

    /**
     * Set the users service.
     * @param newUsersService newUsersService
     */
    @Autowired
    public void setUsersService(final UsersService newUsersService) {
        this.usersService = newUsersService;
    }

    /**
     * showLogin method.
     * @param model model
     * @return a String
     */
    @RequestMapping("/login")
    public String showLogin(final Model model) {

        model.addAttribute("user", new User());
        return "login";
    }

    /**
     * showDenied method.
     * @return a String
     */
    @RequestMapping("/denied")
    public String showDenied() {
        return "denied";
    }

    /**
     * Display the admin page.
     * @param model medel
     * @return the admin page as a String
     */
    @RequestMapping("/admin")
    public String showAdmin(final Model model)
    {

        List<User> users = usersService.getAllUsers();

        model.addAttribute("users", users);

        return "admin";
    }

    /**
     * showNewAccount method.
     * @param model model
     * @return a String
     */
    @RequestMapping("/newaccount")
    public String showNewAccount(final Model model) {

        model.addAttribute("user", new User());
        return "newaccount";
    }

    /**
     * showLogout method.
     * @return a String
     */
    @RequestMapping("/logout")
    public String showLoggedOut() {

        return "logout";
    }

    /**
     * createAccount method.
     * @param user the user
     * @param result result
     * @return the user as a String
     */
    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public String createAccount(@Valid final User user, final Authority authority,
                                final BindingResult result) {

        if (result.hasErrors()) {
            return "newaccount";
        }

        authority.setAuthority("ROLE_USER");
        user.setEnabled(true);

        if (usersService.exists(user.getUsername())) {
            result.rejectValue("username", "DuplicateKey.user.username");
            return "newaccount";
        }

        try {
            usersService.create(user, authority);
        } catch (DuplicateKeyException e) {
            result.rejectValue("username", "DuplicateKey.user.username");
            return "newaccount";
        }

        return "accountcreated";
    }
}
