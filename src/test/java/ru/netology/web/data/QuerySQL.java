package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class QuerySQL {

    private final static QueryRunner runner = new QueryRunner();


    @SneakyThrows
    public static String getVerificationCodeFor(String user) {
        val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/appdb", "user", "pass"
        );
        val idSQL = "SELECT id FROM users WHERE login = '" + user + "'";
        String id = runner.query(conn, idSQL, new ScalarHandler<>());
        val code = "SELECT code FROM auth_codes WHERE user_id = '" + id + "' ORDER BY created DESC";
        return runner.query(conn, code, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void addUser(String id, String user, String password) {
        val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/appdb", "user", "pass"
        );

        val addSQL = "INSERT INTO users(id, login, password) VALUES (?, ?, ?);";
        runner.update(conn, addSQL, id, user, password);

    }

    @SneakyThrows
    public static String getStatusFor(String user) {
        val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/appdb", "user", "pass"
        );

        val statusSQL = "SELECT status FROM users WHERE login = '" + user + "'";
        return runner.query(conn, statusSQL, new ScalarHandler<>());
    }

}

