package com.koffskiy.spring;

import com.koffskiy.data.TeamMember;
import com.koffskiy.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Sergii on 11.12.2015.
 */
@Component
public class DatabasePreloader implements CommandLineRunner {

    private final TeamMemberRepository repository;

    @Autowired
    public DatabasePreloader(TeamMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new TeamMember("Kiril", "kiril.png", 1L));
        this.repository.save(new TeamMember("Oleksandr", "alex.png", 2L));
        this.repository.save(new TeamMember("Sergii", "serg.png", 3L));
    }
}
