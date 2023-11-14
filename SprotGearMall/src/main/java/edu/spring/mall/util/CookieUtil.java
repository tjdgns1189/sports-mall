package edu.spring.mall.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    private static final String KEY = "enthusiastically"; 

    public static String encrypt(String value) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encrypted) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] original = cipher.doFinal(decodedBytes);
        return new String(original);
    }

    public static void createEncryptedCookie(HttpServletResponse response, String name, String value, int maxAge) throws Exception {
        String encryptedValue = encrypt(value);
        Cookie cookie = new Cookie(name, encryptedValue);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        for(Cookie x : cookies) {
        	if(x.getName().equals(name)) {
        		x.setMaxAge(0);
        		x.setPath("/");
        		response.addCookie(x);
        	}
        }

    }

    
    public static String getDecryptedCookieValue(HttpServletRequest request, String name) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return decrypt(cookie.getValue());
                }
            }
        }
        return null;
    }
}