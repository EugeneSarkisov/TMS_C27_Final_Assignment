package com.teachmeskills.final_assignment.util.storage;

/**
 * An improvised storage facility. It contains strings storing login and password.
 * It is used by the Encoder class.
 *
 * @author KirillPalianitsa
 */

import com.teachmeskills.final_assignment.util.encoder.Encoder;

public final class StorageMock extends Encoder {

    /**
     * @param login - contains user login data
     * @param password - contains user password data
     */

    private final String login = "z9r669g68rcXdlcnR5";
    private final String password = "wajbbaievkMTIzNDVBQkM=";

    public String getLogin() {

        return login;
    }

    public String getPassword() {

        return password;
    }
}
