package com.teachmeskills.final_assignment.authorization;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static com.teachmeskills.final_assignment.util.consts.session_parameters.SessionParameters.*;

/**
 * Pattern for Session objects for validation processing. Uses for
 * verification and stating processing if session object is exist.
 *
 * @author KirillPalianitsa
 */

public final class Session {

    /**
     * String accessToken contain random letters and numbers from SessionParameters interface.
     */
    private String accessToken;

    /**
     * Date expDate contain lifetime for Session objects.
     */
    private Date expDate;

    /**
     * Constructor for Session objects. Access to parameters getting by
     * setAccessToken and setExpDate methods.
     */

    public Session() {
        setAccessToken();
        setExpDate();
    }

    /**
     * Method implements verification of Session object and checking if session is alive.
     *
     * @return - true if accessToken length equals length in SessionParameters and if date
     * of expiring isn't equals date of now.
     */

    public boolean isSessionStillAlive() {
        if (this.accessToken.length() == ACCESS_TOKEN_LENGTH &&
                this.expDate.after(new Date())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets new access token by StreamAPI methods.
     */
    private void setAccessToken() {
        String symbols = SESSION_GEN_SYMBOLS;

        this.accessToken = new Random().ints(ACCESS_TOKEN_LENGTH, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Objects::toString)
                .collect(Collectors.joining());
    }

    /**
     * Sets date of token expiring by DateAPI methods.
     */

    private void setExpDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, SESSION_LIFETIME);
        this.expDate = calendar.getTime();
    }
}


