package com.workintech.s17d2.model;

import org.springframework.stereotype.Component;


public class MidDeveloper extends Developer {
    public MidDeveloper(Integer id, String name, Double salary) {
        super(id, name, salary, Experience.MID);
    }
}
