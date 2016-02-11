package com.koffskiy.data;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Sprint {

    @Id
    @GeneratedValue
    private Long id;
    private String sprintName;
    private Date start;
    private Date end;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
    private List<StoryPlan> plans;
}
