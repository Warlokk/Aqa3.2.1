package ru.netology.web.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;
import static ru.netology.web.data.QuerySQL.getStatusFor;
import static ru.netology.web.data.QuerySQL.getVerificationCodeFor;


public class SqlTest {
    private DashboardPage dashboardPage;
    private LoginPage loginPage;


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLogin() {
        var user = getAuthInfo();
        var login = new LoginPage().validLogin(user);
        dashboardPage = login.validVerify(getVerificationCodeFor(user.getLogin()));
    }

    @Test
    void shouldAddUserAndLogin() {
        var fakeUser = getRandomAuthInfo();
        var login = new LoginPage().validLogin(fakeUser);
        dashboardPage = login.validVerify(getVerificationCodeFor(fakeUser.getLogin()));
    }

    @Test
    void shouldBlockIncorrectPassword() {
        var userPass = new AuthInfo(getAuthInfo().getId(), getAuthInfo().getLogin(), "qawsedrf12");
        loginPage = new LoginPage();
        loginPage.login(userPass);
        loginPage.error();
        loginPage.login(userPass);
        loginPage.error();
        loginPage.login(userPass);
        loginPage.error();
        String actual = getStatusFor(getAuthInfo().getLogin());
        assertEquals("blocked", actual);
    }
}
