package com.wplab.homework3.result.service;

import com.wplab.homework3.core.Transactional;
import com.wplab.homework3.question.repository.UserRepository;

public class ResetService implements IResetService {
    private final UserRepository repo;
    public ResetService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void remove(String email) {
        repo.delete(email);
    }
}
