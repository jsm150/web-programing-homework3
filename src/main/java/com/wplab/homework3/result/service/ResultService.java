package com.wplab.homework3.result.service;

import com.wplab.homework3.core.Transactional;
import com.wplab.homework3.entity.Result;
import com.wplab.homework3.result.DO.ResultResponse;
import com.wplab.homework3.result.repository.ResultRepository;

import java.util.ArrayList;
import java.util.Map;

public class ResultService implements IResultService {
	private final ResultRepository repo;

    public ResultService(ResultRepository repo) {
        this.repo = repo;
    }

    @Override
	@Transactional
	public ResultResponse evaluate(String email, String name, ArrayList<String> values) {
		int scoreSum = 0;
		for (String score : values) {
			scoreSum += Integer.parseInt(score);
		}

		int index = 1;
		if (scoreSum > 20) index = (scoreSum - 1) / 10;

		var content = repo.getContent(index);
		var result = new Result();
		result.Email = email;
		result.Value = scoreSum;
		result.ResultIndex = content.Index;
		repo.save(result);

		return new ResultResponse(name, scoreSum, content.Content);
	}


}
