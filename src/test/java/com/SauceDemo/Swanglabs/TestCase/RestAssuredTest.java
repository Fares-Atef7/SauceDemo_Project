package com.SauceDemo.Swanglabs.TestCase;

import com.SauceDemo.Swanglabs.API.RegisterAPI;
import com.SauceDemo.Swanglabs.Bases.BaseTest;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.util.List;

public class RestAssuredTest extends BaseTest {
    @Test
    public void shouldLoginUsingCookies() {
        // 1. جلب الـ Cookies من الـ API (باستخدام كلاس RegisterAPI الذي أرسلته سابقاً)
        RegisterAPI registerAPI = new RegisterAPI();
        registerAPI.Register();
        List<Cookie> restAssuredCookies = registerAPI.getrestassuredCookies();

        // 2. فتح الموقع أولاً بالمتصفح (خطوة إجبارية قبل حقن أي كوكيز)
        getDriver().get("https://qacart-todo.herokuapp.com");

        // 3. استدعاء الميثود وحقن الكوكيز داخل المتصفح
        injectCookiestoBrowser(restAssuredCookies);

        // 4. الانتقال إلى صفحة الـ Todo الداخلية مباشرة بصفتك مسجل دخول
        getDriver().get("https://qacart-todo.herokuapp.com/todo");

        // اختياري: تأكيد نجاح الدخول عن طريق التحقق من أي عنصر داخل الصفحة
    }
}
