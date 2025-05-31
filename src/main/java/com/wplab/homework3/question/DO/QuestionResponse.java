package com.wplab.homework3.question.DO;

import com.wplab.homework3.entity.Question;

import java.util.ArrayList;

public class QuestionResponse {
    private final String name;
    private final int nextPage;
    private final ArrayList<Question> questions;

    public QuestionResponse(String name, int nextPage, ArrayList<Question> questions) {
        this.name = name;
        this.nextPage = nextPage;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getNextPage() {
        return nextPage;
    }
}


