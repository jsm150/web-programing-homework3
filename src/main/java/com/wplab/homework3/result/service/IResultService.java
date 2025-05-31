package com.wplab.homework3.result.service;

import com.wplab.homework3.result.DO.ResultResponse;

import java.util.ArrayList;

public interface IResultService {
    ResultResponse evaluate(String email, String name, ArrayList<String> values);
}
