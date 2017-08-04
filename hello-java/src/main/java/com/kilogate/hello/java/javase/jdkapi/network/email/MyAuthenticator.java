package com.kilogate.hello.java.javase.jdkapi.network.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 认证
 */
public class MyAuthenticator extends Authenticator {
    private String username;
    private String password;

    public MyAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
