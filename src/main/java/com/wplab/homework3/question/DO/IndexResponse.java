package com.wplab.homework3.question.DO;

public class IndexResponse {
    private final String email;
    private final String name;
    private final String password;
    private final String notice;

    public IndexResponse(String email, String name, String password, String notice) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.notice = notice;
    }

    public String getEmail() {
        return email;
    }

    public String getNotice() {
        return notice;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
