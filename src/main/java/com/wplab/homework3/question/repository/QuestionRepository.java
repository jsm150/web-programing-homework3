package com.wplab.homework3.question.repository;

import com.wplab.homework3.core.BaseRepository;
import com.wplab.homework3.core.QueryResult;
import com.wplab.homework3.entity.Question;
import com.wplab.homework3.entity.QuestionContent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionRepository extends BaseRepository {
    private QuestionContent createQuestionContent(Question question, ResultSet rs) throws SQLException {
        var questionContent = new QuestionContent();
        questionContent.Content = rs.getString("content");
        questionContent.QuestionNumber = rs.getInt("question_number");
        questionContent.Question = question;
        questionContent.ItemNumber = rs.getInt("item_number");
        questionContent.Value = rs.getInt("value");
        return questionContent;
    }

    public ArrayList<Question> take(int start, int limit) throws SQLException {
        int end = start + limit;

        try (QueryResult query = executeQuery("SELECT * FROM Question q " +
                "JOIN QuestionContent qc ON q.number = qc.question_number " +
                "WHERE number >= ? AND number < ? " +
                "ORDER BY number", start, end)) {
            var rs = query.Set;
            int prev = 0;
            ArrayList<Question> result = new ArrayList<>();
            ArrayList<QuestionContent> contents = new ArrayList<>();
            Question question = null;

            while (rs.next()) {
                int number = rs.getInt("number");
                if (prev != number) {
                    prev = number;
                    contents = new ArrayList<>();

                    question = new Question();
                    question.Number = number;
                    question.Title = rs.getString("title");
                    question.Contents = contents;
                    question.Contents.add(createQuestionContent(question, rs));

                    result.add(question);
                }
                else {
                    contents.add(createQuestionContent(question, rs));
                }
            }

            return result;
        }
    }
}
