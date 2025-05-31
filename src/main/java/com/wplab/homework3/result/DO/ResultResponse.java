package com.wplab.homework3.result.DO;

public class ResultResponse {
	private final String name;
	private final int score;
	private final String result;
	
	public ResultResponse(String name, int score, String result) {
        this.name = name;
        this.score = score;
		this.result = result;
	}

	public int getScore() {
		return score;
	}

	public String getResult() {
		return result;
	}

    public String getName() {
        return name;
    }
}
