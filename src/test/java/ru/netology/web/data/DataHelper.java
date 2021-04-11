package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.*;


@Data
public class DataHelper {

    @Value
    public static class AuthInfo {
        private String id;
        private String login;
        private String password;

    }


    public static AuthInfo getAuthInfo() {
        return new AuthInfo("11a8163d-d956-435e-b61d-e036594540c7", "vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("a4beae7a-ff77-470b-a087-6e147ffaabc7", "petya", "123qwerty");
    }

    public static AuthInfo getRandomAuthInfo() {
        val faker = new Faker();
        val id = faker.idNumber().valid();
        val name = faker.name().username();
        val password = "$2a$10$UvF1b6JIdHKpe0Wk8nILz.S4iTbBDSXrfDThlY2PKw5tUHGAHwo62"; //password is "123qwerty"
        QuerySQL.addUser(id, name, password);
        return new AuthInfo(id, name, "123qwerty");
    }


}
