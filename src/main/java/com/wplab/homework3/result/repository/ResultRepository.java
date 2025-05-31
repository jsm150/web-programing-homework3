package com.wplab.homework3.result.repository;

import com.wplab.homework3.core.BaseRepository;
import com.wplab.homework3.entity.Result;
import com.wplab.homework3.entity.ResultContent;

import java.sql.SQLException;

public class ResultRepository extends BaseRepository {

    public ResultContent getContent(int index) {
        try (var query = executeQuery("SELECT * FROM ResultContent " +
                "WHERE \"index\" = ?", index)) {
            var rs = query.Set;
            if (rs.next()) {
                var result = new ResultContent();
                result.Index = index;
                result.Content = rs.getString("content");
                return result;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Result result) {
        try {
            executeCommand("INSERT INTO Result " +
                    "Values(?, ?, ?)", result.Email, result.Value, result.ResultIndex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
