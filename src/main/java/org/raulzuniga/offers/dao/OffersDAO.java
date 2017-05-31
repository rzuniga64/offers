package org.raulzuniga.offers.dao;

import org.raulzuniga.offers.models.Offer;
import org.raulzuniga.offers.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.raulzuniga.offers.WebMvcConfig.dataSource;

/**
 *  OffersDAO class. This will be our data source bean for the project.  The
 *  compiler expects one bean to qualify as a autowired candidate for javax.sql.
 *  DataSource.  It won't be able to run setDataSource because it won't ba able
 *  to find a bean to inject into the @Autowired setDataSource(...). We need to
 *  tell Spring to create that. Open your Data Access Object file,
 *  dao-context.xml, and add a jee namespace and jee schemaLocation. Then add
 *  the expected-type class in a jee:jndi lookup. The jndi-name is the name of
 *  the DataSource you need to setup in the Application Server. The setup is
 *  specific to the server you are using.  You need to specify it as a
 *  resource-ref in your web.xml file.  This will create a javax.sql.DataSource
 *  that can be autowired in this class.
 *
 *  Then we add a Service layer to coordinate different data access objects to
 *  the Controller that needs them. Then the Controller can send the data to
 *  the View. Was being used from App.java but now we are going to use a
 *  Controller.
 */
@Component
public class OffersDAO {

    /** JDBC template. */
    private NamedParameterJdbcTemplate jdbc;

    /** Constructor. */
    public OffersDAO() {

        System.out.println("Succesfully loaded offers DAO");
    }

    /**
     *  Get JDBC connection.
     *  @return JDBC connection
     */
    public NamedParameterJdbcTemplate getJdbc() {

        return jdbc;
    }

    /**
     *  Set JDBC connection.
     *  Pass a DataSource instead of a JdbcTemplate to get access to some great
     *  methods from DataSource which comes from org.apache.commons.dbcp.
     *  BasicDataSource.
     */
    @Autowired
    public void setJdbcTemplate() {

        this.jdbc = new NamedParameterJdbcTemplate(dataSource());
    }

    /**
     *  Create an Offer object in the database.
     *  Create a set of parameters you can use to replace placeholders in your
     *  SQL from your actual bean properties.
     *
     * @param offer offer
     * @return true if an Offer object is returned; false otherwise
     */
    public boolean create(Offer offer) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                offer);

        return jdbc
                .update("insert into offers (username, text) values (:username, :text)",
                        params) == 1;
    }

    /**
     *  Batch update Offer objects in the database using Spring (not JDBC).
     *  Having the @Transactional annotation means that everything that is done
     *  in terms of SQL will either all succeed or all fail.
     *  @param offers offers
     *  @return an array of integers
     */
    @Transactional
    public int[] create(final List<Offer> offers) {

        SqlParameterSource[] params = SqlParameterSourceUtils
                .createBatch(offers.toArray());

        return jdbc.batchUpdate("insert into springtutorial.offers (username, text)"
                + "values (:username, :text)", params);
    }

    /**
     *  Update an Offer object in the database.
     *  @param offer offer
     *  @return true if an Offer object is returned; false otherwise
     */
    public boolean update(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                offer);

        return jdbc.update("update offers set text=:text where id=:id", params) == 1;
    }

    /**
     *  Delete a row based on id.
     *  @return true if row is deleted; false otherwise.
     */
    public boolean delete(final int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.update("delete from springtutorial.offers "
                + "where id =:id", params) == 1;
    }

    /**
     *  Get offer.
     *  @return offer
     */
    public Offer getOffer(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from offers, users "
                        + "where offers.username=users.username "
                        + "and users.enabled=true"
                        + "and id = :id", params,
                        new RowMapper<Offer>() {

                    public Offer mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        User user = new User();
                        user.setUsername(rs.getString("username"));
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        user.setEnabled(true);
                        user.setAuthority(rs.getString("authority"));

                        Offer offer = new Offer();
                        offer.setId(rs.getInt("id"));
                        offer.setText(rs.getString("text"));
                        offer.setUser(user);

                        return offer;
                    }
        });
    }

    /**
     *  Get offers.
     *  @return offers
     */
    public List<Offer> getOffers() {

        return jdbc.query("select * from offers, users "
                + "where offers.username=users.username and users.enabled=true",
                new RowMapper<Offer>() {

            @Override
            public Offer mapRow(final ResultSet rs, int rowNum)
                    throws SQLException {

                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setEnabled(true);
                user.setAuthority(rs.getString("authority"));

                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setText((rs.getString("text")));
                offer.setUser(user);

                return offer;
            }
        });
    }
}
