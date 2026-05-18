package Utilis;

import Objects.User;
import com.github.javafaker.Faker;


public class UserFakeGeneration {
    public static User UserDetails() {
        String firstName = new Faker().name().firstName();
        String lastName = new Faker().name().lastName();
        String email = new Faker().internet().emailAddress();
        String password = new Faker().internet().password();
        User user = new User(firstName, lastName, email, password);
        return user;
    }
}
