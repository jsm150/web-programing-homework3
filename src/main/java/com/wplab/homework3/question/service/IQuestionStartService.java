package com.wplab.homework3.question.service;

import com.wplab.homework3.entity.User;

import javax.security.auth.login.LoginException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface IQuestionStartService {
    User loginOrRegister(String email, String username, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, LoginException;
}
