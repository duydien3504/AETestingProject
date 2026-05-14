package DataGenerate;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RegisterGenData {
    private static final Faker faker =  new Faker(new Locale("vi"));

    public static String genEmail() {
        return faker.internet().emailAddress();
    }
}
