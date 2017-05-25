package org.raulzuniga.offers.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/** User class. */
public class Authority {
    /** User name. */
    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^\\w{8,}$")
    private String username;

    /** Authority. */
    private String authority;

    /** Constructor. */
    public Authority() { }

    /**
     * Constructor.
     * @param newUsername newUsername
     * @param newAuthority newAuthority
     * */
    public Authority(final String newUsername,
                     final String newAuthority) {
        this.username = newUsername;
        this.authority = newAuthority;
    }

    /**
     * getUsername.
     * @return user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * setUserName.
     * @param newUsername newUsername
     */
    public void setUsername(final String newUsername) {
        this.username = newUsername;
    }

    /**
     * getAuthority.
     * @return authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * setAuthority.
     * @param newAuthority newAuthority
     */
    public void setAuthority(final String newAuthority) {
        this.authority = newAuthority;
    }
}

