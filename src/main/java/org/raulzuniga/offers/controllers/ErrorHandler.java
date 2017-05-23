package org.raulzuniga.offers.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLSyntaxErrorException;

/**
 *  DatabaseErrorHandler class.
 */
@ControllerAdvice
public class ErrorHandler {

    /**
     *  handleDatabaseException method.
     *  @param ex exception
     *  @return a error String
     */
    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(final DataAccessException ex) {
        ex.printStackTrace();
        return "error";
    }

    /**
     * handleAccessException method.
     * @param ex ex
     * @return a String
     */
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessException(final AccessDeniedException ex) {

        return "denied";
    }

    /**
     * handleSQLSyntaxErrorException method.
     * @param ex ex
     * @return a String
     */
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public String handleAccessException(final SQLSyntaxErrorException ex) {

        return "denied";
    }
}
