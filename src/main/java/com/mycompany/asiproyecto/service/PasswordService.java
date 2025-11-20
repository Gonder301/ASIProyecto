package com.mycompany.asiproyecto.service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

public class PasswordService {
    private static final int ITERATIONS = 2;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 1;
    
    public static String hash(char[] password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
        try {
            return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, password);
        }
        finally {
            argon2.wipeArray(password);
        }
    }
}
