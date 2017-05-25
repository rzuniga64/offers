package org.raulzuniga.offers.dao;

import org.raulzuniga.offers.models.Authority;
import org.raulzuniga.offers.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Users data access object.
 */
@Component("usersDao")
public class UsersDAO {

    /** JDBC template. */
    private JdbcTemplate jdbc;

    /** Password encoder. */
    //@Resource
    //private PasswordEncoder passwordEncoder;

    /** Constructor. */
    public UsersDAO() { }

    /**
     *  Get JDBC connection.
     *  @return JDBC connection
     */
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbc;
    }

    /**
     *  To configure your own DataSource define a @Bean of that type in your
     *  configuration. Spring Boot will reuse your DataSource anywhere one is
     *  required, including database initialization.
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {

        ClassPathXmlApplicationContext ctx
                = new ClassPathXmlApplicationContext("datasource.xml");
        return (DataSource) ctx.getBean("dataSource");
    }

    /**
     *  Set JDBC connection.
     *  Pass a DataSource instead of a JdbcTemplate to get access to some great
     *  methods from DataSource which comes from org.apache.commons.dbcp.
     *  BasicDataSource.
     */
    @Autowired
    public void setJdbcTemplate() {
        this.jdbc = new JdbcTemplate(dataSource());
    }

    /**
     *  Create a User object in the database.
     *  Create a set of parameters you can use to replace placeholders in your
     *  SQL from your actual bean properties.
     *
     * @param user user
     * @return true if an Offer object is returned; false otherwise
     */
    @Transactional
    public boolean create(final User user, final Authority authority) {

        jdbc.update("insert into springtutorial.users (username, password, email, enabled) "
                + "values(?, ?, ?, ?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setBoolean(4, user.getEnabled());
            }
        });

        return jdbc.update("insert into springtutorial.authorities (username, authority) "
                + "values(?, ?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, authority.getUsername());
                ps.setString(2, authority.getAuthority());
            }
        }) == 1;
    }

    /**
     * Username exists?
     * @param username username
     * @return true if exists; false otherwise
     */
    public boolean exists(final String username) {

       // MapSqlParameterSource params
                //= new MapSqlParameterSource("username", username);

        //SqlRowSet srs = getJdbcTemplate().queryForRowSet("select count(*) from springtutorial.users where binary username=:username", params);
        //return (srs.next());
        return false;
    }

    /**
     * Take the SQL result and gives a RowMapper that maps each row to a User
     * bean.
     * @return all users
     */
    public List<User> getAllUsers() {

        return jdbc.query("select * from springtutorial.users, "
                        + "springtutorial.authorities "
                        + "where binary users.username=authorities.username",
                new BeanPropertyRowMapper<User>(User.class));
    }

    /**
     *  Get all users. Alternative method to getAllUsers (not used).
     *  @return offers
     */
    public List<User> getUsers() {

        return jdbc.query("select * from springtutorial.users, "
                + "springtutorial.authorities where binary users.username=authorities.username",
                new RowMapper<User>() {

            public User mapRow(final ResultSet resultSet, final int i)
                    throws SQLException {

                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setAuthority(resultSet.getString("authority"));
                user.setEnabled(resultSet.getBoolean("enabled"));

                return user;
            }
        });
    }
}
