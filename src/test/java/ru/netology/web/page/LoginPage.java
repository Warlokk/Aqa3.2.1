package ru.netology.web.page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.DELETE;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement wrongInput = $(withText("Ошибка!"));


    public void login(DataHelper.AuthInfo info) {
        loginField.sendKeys(CONTROL + "a", DELETE);
        loginField.setValue(info.getLogin());
        passwordField.sendKeys(CONTROL + "a", DELETE);
        passwordField.setValue(info.getPassword());
        loginButton.click();

    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login(info);
        return new VerificationPage();
    }

    public void error() {
        wrongInput.shouldBe(visible);
    }
}
