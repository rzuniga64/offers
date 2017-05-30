package org.raulzuniga.offers.models;

import javax.validation.constraints.Size;

/**
 *  Offer class.
 */
public final class Offer {

    /** ID. */
    private int id;

    /** Text. */
    @Size(  min = 20, max = 100,
            message = "Text must be between 20 and 100 characters." )
    private String text;

    /** User. */
    private User user;

    /** User. */
    private String username;

    /** Default Constructor. */
    public Offer() { }

    /**
     * Constructor.
     *  @param newId newId
     *  @param newUser newUser
     *  @param newText newText
     */
    public Offer(final int newId, final User newUser, final String newText) {

        this.id = newId;
        this.user = newUser;
        this.text = newText;
    }

    /**
     * Constructor.
     *  @param newUser newUser
     *  @param newText newText
     */
    public Offer(final User newUser, final String newText) {

        this.user = newUser;
        this.text = newText;
    }

    /**
     * Get the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     *  Set the id.
     * @param newId newId
     */
    public void setId(final int newId) {
        this.id = newId;
    }

    /**
     *  Get the text.
     * @return the text.
     */
    public String getText() {
        return text;
    }

    /**
     *  Set text.
     *  @param newText newText
     */
    public void setText(final String newText) {
        this.text = newText;
    }

    /**
     *  Get the user.
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     *  Set user.
     *  @param newUser newUser
     */
    public void setUser(User newUser) {

        this.user = newUser;
    }

    /**
     *  Get the username.
     * @return the username.
     */
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * hashCode method.
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    /**
     * Equals method.
     * @param obj obj
     * @return true if equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Offer other = (Offer) obj;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    /**
     * toString method.
     * @return Offer state as a string.
     */
    @Override
    public String toString() {
        return "Offer [id=" + id + ", text=" + text + ", user=" + user + "]";
    }


}
