package com.wplab.homework3.question.repository;

import com.wplab.homework3.core.BaseRepository;
import com.wplab.homework3.core.QueryResult;
import com.wplab.homework3.entity.Result;
import com.wplab.homework3.entity.ResultContent;
import com.wplab.homework3.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends BaseRepository {
    public User findBy(String email) throws SQLException {
        try (QueryResult query = executeQuery("select user.email, name, password_hash, value, content from user " +
                "outer left join result r on r.email = user.email " +
                "outer left join ResultContent rc ON r.result_index = rc.\"index\" " +
                "WHERE user.email = ?", email)) {
            var rs = query.Set;
            if (rs.next()) {
                User user = new User();
                user.Email = rs.getString("email");
                user.Name = rs.getString("name");
                user.PasswordHash = rs.getString("password_hash");

                var value = rs.getInt("value");
                var content = rs.getString("content");
                var result = new Result();
                result.Content = new ResultContent();
                result.Value = value;
                result.Content.Content = content;

                if (content != null) {
                    user.Result = result;
                }
                return user;
            }
        }
        return null;
    }

    public void save(User user) throws SQLException {
        executeCommand("INSERT INTO User VALUES (?, ?, ?)", user.Email, user.Name, user.PasswordHash);
    }

    public void update(User user) throws SQLException {
        executeCommand("UPDATE User SET name = ?, password_hash = ? WHERE email = ?",
                user.Name, user.PasswordHash, user.Email);
    }

    public void delete(String email){
        try {
            executeCommand("DELETE FROM User WHERE email = ?", email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
