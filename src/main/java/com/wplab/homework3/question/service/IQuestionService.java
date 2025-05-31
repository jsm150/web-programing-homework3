package com.wplab.homework3.question.service;

import com.wplab.homework3.question.DO.QuestionResponse;

import java.sql.SQLException;

public interface IQuestionService {
    QuestionResponse getContent(int page, String name) throws SQLException;
}
