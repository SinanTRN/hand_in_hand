package com.example.hand_in_hand.security.password;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Parolayı hashleme metodu
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Parola doğrulama metodu
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

