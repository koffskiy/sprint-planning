package com.koffskiy.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Story {

    @Id
    @GeneratedValue
    private Long id;
    private String storyName;
    private String storyDescription;
}
