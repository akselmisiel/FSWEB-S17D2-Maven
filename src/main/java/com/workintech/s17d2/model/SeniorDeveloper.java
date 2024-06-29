package com.workintech.s17d2.model;

import org.springframework.stereotype.Component;


public class SeniorDeveloper extends Developer {
    public SeniorDeveloper(Integer id, String name, Double salary) {
        super(id, name, salary, Experience.SENIOR);
    }
}
