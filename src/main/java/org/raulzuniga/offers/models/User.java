package org.raulzuniga.offers.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/** User class. */
public class User {
    /** User name. */
    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^\\w{8,}$")
    private String username;

    /** Password. */
    @NotBlank
    @Pattern(regexp = "^\\S+$")
    @Size(min = 8, max = 15)
    private String password;

    /** Email. */
    private String email;
    /** enabled? */
    private boolean enabled = false;
    /** Authority. */
    private String authority;

    /** Constructor. */
    public User() { }

    /**
     * Constructor.
     * @param newUsername newUsername
     * @param newPassword newPassword
     * @param newEmail newEmail
     * @param newEnabled newEnabled
     * @param newAuthority newAuthority
     * */
    public User(final String newUsername,
                final String newPassword,
                final String newEmail,
                final boolean newEnabled,
                final String newAuthority) {
        this.username = newUsername;
        this.password = newPassword;
        this.email = newEmail;
        this.enabled = newEnabled;
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
     * getPassword.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword.
     * @param newPassword newPassword
     */
    public void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    /**
     * getEmail.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail.
     * @param newEmail newEmail
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail;
    }

    /**
     * isEnabled?
     * @return true if enabled;false otherwise
     */
    public boolean getEnabled() {
        return enabled;
    }

    /**
     * setEnabled.
     * @param newEnabled newEnabled
     */
    public void setEnabled(final boolean newEnabled) {
        this.enabled = newEnabled;
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

