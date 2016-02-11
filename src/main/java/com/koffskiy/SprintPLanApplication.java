package com.koffskiy;

import com.sun.jersey.api.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class SprintPLanApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintPLanApplication.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    public String jiraUrl() {
        return "https://jira.bank.swissquote.ch";
    }
}
