package util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    public static String hashPassword(String password){
        return BCrypt.withDefaults().hashToString(12,password.toCharArray());
    }
    public static boolean verifyPassword(String planPassword,String dbPassword){
        return BCrypt.verifyer().verify(planPassword.toCharArray(),dbPassword).verified;
    }
}