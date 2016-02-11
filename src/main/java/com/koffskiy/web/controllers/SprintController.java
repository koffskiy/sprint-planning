package com.koffskiy.web.controllers;

import com.koffskiy.repository.StoryPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("api/sprints")
public class SprintController {
    @Autowired
    StoryPlanRepository planRepository;

    @RequestMapping
    public List<String> all() {

        List<String> collect = new ArrayList<>();

        return collect;
    }

}
