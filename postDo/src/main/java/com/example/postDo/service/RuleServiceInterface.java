package com.example.postDo.service;

import java.util.List;

import com.example.postDo.entity.Rule;

public interface RuleServiceInterface {

	Rule findOne(Integer id);
	
	List<Rule> findAll();
	
	Rule save(Rule rule);
	
	void remove(Integer id);
}