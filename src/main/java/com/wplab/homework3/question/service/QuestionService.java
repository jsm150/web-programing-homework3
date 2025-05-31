package com.wplab.homework3.question.service;

import com.wplab.homework3.core.Transactional;
import com.wplab.homework3.question.DO.QuestionResponse;
import com.wplab.homework3.question.repository.QuestionRepository;

import java.sql.SQLException;

public class QuestionService implements IQuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional
    public QuestionResponse getContent(int page, String name) throws SQLException {
        int start = page * 2 - 1;
        var questions = questionRepository.take(start, 2);
        return new QuestionResponse(name, page + 1, questions);
    }
}
