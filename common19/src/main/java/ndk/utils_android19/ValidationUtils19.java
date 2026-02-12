package ndk.utils_android19;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class ValidationUtils19 {

    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Patterns.EMAIL_ADDRESS;
    
    // Phone number pattern (basic validation for digits and common separators)
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[+]?[0-9\\s\\-()]+$");
    
    // URL validation pattern
    private static final Pattern URL_PATTERN = Patterns.WEB_URL;
    
    // Password pattern: at least 8 characters, one uppercase, one lowercase, one digit
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

    /**
     * Check if a string is null or empty
     * @param text the text to check
     * @return true if text is null or empty, false otherwise
     */
    public static boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }

    /**
     * Check if a string is not null and not empty
     * @param text the text to check
     * @return true if text is not null and not empty, false otherwise
     */
    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    /**
     * Validate email address
     * @param email the email to validate
     * @return true if valid email, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return isNotEmpty(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validate phone number
     * @param phone the phone number to validate
     * @return true if valid phone number, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        return isNotEmpty(phone) && PHONE_PATTERN.matcher(phone).matches() && phone.length() >= 10;
    }

    /**
     * Validate URL
     * @param url the URL to validate
     * @return true if valid URL, false otherwise
     */
    public static boolean isValidUrl(String url) {
        return isNotEmpty(url) && URL_PATTERN.matcher(url).matches();
    }

    /**
     * Validate password strength
     * Password must be at least 8 characters with at least one uppercase, one lowercase, and one digit
     * @param password the password to validate
     * @return true if password meets criteria, false otherwise
     */
    public static boolean isValidPassword(String password) {
        return isNotEmpty(password) && PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Validate minimum length
     * @param text the text to check
     * @param minLength minimum required length
     * @return true if text length is at least minLength, false otherwise
     */
    public static boolean hasMinLength(String text, int minLength) {
        return isNotEmpty(text) && text.length() >= minLength;
    }

    /**
     * Validate maximum length
     * @param text the text to check
     * @param maxLength maximum allowed length
     * @return true if text length is at most maxLength, false otherwise
     */
    public static boolean hasMaxLength(String text, int maxLength) {
        return isEmpty(text) || text.length() <= maxLength;
    }

    /**
     * Validate that text is within a length range
     * @param text the text to check
     * @param minLength minimum required length
     * @param maxLength maximum allowed length
     * @return true if text length is within range, false otherwise
     */
    public static boolean isLengthInRange(String text, int minLength, int maxLength) {
        return hasMinLength(text, minLength) && hasMaxLength(text, maxLength);
    }

    /**
     * Check if a string contains only alphabetic characters
     * @param text the text to check
     * @return true if text contains only letters, false otherwise
     */
    public static boolean isAlphabetic(String text) {
        return isNotEmpty(text) && text.matches("[a-zA-Z]+");
    }

    /**
     * Check if a string contains only numeric characters
     * @param text the text to check
     * @return true if text contains only digits, false otherwise
     */
    public static boolean isNumeric(String text) {
        return isNotEmpty(text) && text.matches("\\d+");
    }

    /**
     * Check if a string contains only alphanumeric characters
     * @param text the text to check
     * @return true if text contains only letters and digits, false otherwise
     */
    public static boolean isAlphanumeric(String text) {
        return isNotEmpty(text) && text.matches("[a-zA-Z0-9]+");
    }

    /**
     * Validate that two strings match (useful for password confirmation)
     * @param text1 first text
     * @param text2 second text
     * @return true if both texts are equal, false otherwise
     */
    public static boolean matches(String text1, String text2) {
        return text1 != null && text1.equals(text2);
    }

    /**
     * Check if a number is within a range
     * @param value the value to check
     * @param min minimum value (inclusive)
     * @param max maximum value (inclusive)
     * @return true if value is within range, false otherwise
     */
    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Check if a number is within a range
     * @param value the value to check
     * @param min minimum value (inclusive)
     * @param max maximum value (inclusive)
     * @return true if value is within range, false otherwise
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    /**
     * Validate credit card number using Luhn algorithm
     * @param cardNumber the card number to validate
     * @return true if valid card number, false otherwise
     */
    public static boolean isValidCreditCard(String cardNumber) {
        if (isEmpty(cardNumber) || !isNumeric(cardNumber)) {
            return false;
        }
        
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    /**
     * Validate Indian mobile number (10 digits starting with 6-9)
     * @param phone the phone number to validate
     * @return true if valid Indian mobile number, false otherwise
     */
    public static boolean isValidIndianMobile(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        String cleanPhone = phone.replaceAll("[\\s\\-()]", "");
        return cleanPhone.matches("^[6-9]\\d{9}$");
    }

    /**
     * Validate US phone number (10 digits)
     * @param phone the phone number to validate
     * @return true if valid US phone number, false otherwise
     */
    public static boolean isValidUSPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        String cleanPhone = phone.replaceAll("[\\s\\-()]", "");
        return cleanPhone.matches("^\\d{10}$");
    }

    /**
     * Validate username (alphanumeric, underscore, 3-20 characters)
     * @param username the username to validate
     * @return true if valid username, false otherwise
     */
    public static boolean isValidUsername(String username) {
        return isNotEmpty(username) && username.matches("^[a-zA-Z0-9_]{3,20}$");
    }
}
