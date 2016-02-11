package com.koffskiy.web.controllers;

import com.koffskiy.data.TeamMember;
import com.koffskiy.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
//@RequestMapping("api/teamMembers")
public class TeamController {
    @Autowired
    TeamMemberRepository repository;

    @RequestMapping
    public Iterable<TeamMember> all() {
        return repository.findAll();
    }

}
