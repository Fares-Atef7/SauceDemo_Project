package com.SauceDemo.Swanglabs.Config;

import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    // Base test class is the best place to use this (inject) method
    public static List<Cookie> ConvertRestAssuredToSeleniumCookie(List<io.restassured.http.Cookie> restassuredCookie) {
        List<Cookie> seleniumCookies = new ArrayList<>();
        for (io.restassured.http.Cookie cookie : restassuredCookie) {
            org.openqa.selenium.Cookie seleniumCookie = new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue());
            seleniumCookies.add(seleniumCookie);
        }
        return seleniumCookies;
    }
}
