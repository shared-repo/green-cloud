package com.demoweb.security;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.demoweb.common.Util;

public class DemoWebPasswordEncoder implements PasswordEncoder {

    private static final String ALGORITHM = "SHA-256";

    @Override
    public String encode(CharSequence rawPassword) {
        return Util.getHashedString(rawPassword.toString(), ALGORITHM);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = Util.getHashedString(rawPassword.toString(), ALGORITHM);

        return hashedPassword.equals(encodedPassword);
    }

}

