package com.teachmeskills.final_assignment.authorization;

/**
 *
 *
 * @author
 */


import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static com.teachmeskills.final_assignment.util.consts.session_parameters.SessionParameters.*;

public final class Session {

    private String accessToken;
    private Date expDate;

    public Session(){
        setAccessToken();
        setExpDate();
    }
    public boolean isSessionStillAlive () {
        if (this.accessToken.length() == ACCESS_TOKEN_LENGTH &&
                this.expDate.after(new Date())) {
            return true;
        } else {
            return false;
        }
    }

    private void setAccessToken(){
        String symbols = SESSION_GEN_SYMBOLS;

        this.accessToken = new Random().ints(ACCESS_TOKEN_LENGTH,0,symbols.length())
                                       .mapToObj(symbols::charAt)
                                       .map(Objects::toString)
                                       .collect(Collectors.joining());
    }
    private void setExpDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,SESSION_LIFETIME);
        this.expDate = calendar.getTime();
        }
    }


