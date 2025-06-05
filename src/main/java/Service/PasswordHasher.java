package Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] salt = new byte[8];
            new SecureRandom().nextBytes(salt);

            digest.update(salt);
            byte[] hashedBytes = digest.digest(password.getBytes());

            byte[] combined = new byte[salt.length + hashedBytes.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hashedBytes, 0, combined, salt.length, hashedBytes.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedHash) {
        try {
            byte[] combined = Base64.getDecoder().decode(storedHash);

            byte[] salt = new byte[8];
            System.arraycopy(combined, 0, salt, 0, salt.length);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] inputHash = digest.digest(inputPassword.getBytes());

            for (int i = 0; i < inputHash.length; i++) {
                if (inputHash[i] != combined[salt.length + i]) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to verify password", e);
        }
    }

    // Add this method to PasswordHasher class
    public static boolean checkPassword(String inputPassword, String storedHash) {
        return verifyPassword(inputPassword, storedHash);
    }
}