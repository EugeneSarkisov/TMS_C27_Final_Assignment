package com.teachmeskills.final_assignment.authorization;

//TODO write javadoc

import com.teachmeskills.final_assignment.parser.ParseDocs;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public final class Session extends ParseDocs {

    private String accessToken;
    private Date expDate;

    public Session(){
        setAccessToken();
        setExpDate();
    }
    public boolean isSessionStillAlive () {
        if (this.accessToken.length() == 16 &&
                this.expDate.after(new Date())) {
            return true;
        } else {
            return false;
        }
    }

    private void setAccessToken(){
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

        this.accessToken = new Random().ints(16,0,symbols.length())
                                       .mapToObj(symbols::charAt)
                                       .map(Objects::toString)
                                       .collect(Collectors.joining());
    }
    private void setExpDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,10);

        this.expDate = calendar.getTime();
        }
    }

