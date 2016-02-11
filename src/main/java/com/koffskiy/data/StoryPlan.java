package com.koffskiy.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class StoryPlan {

    @Id
    @GeneratedValue
    private Long id;

    private Date start;
    private Date end;

    @ManyToOne
    private TeamMember responsible;
    @ManyToOne
    private Sprint sprint;
    @ManyToOne
    private Story story;

    @OneToMany
    private List<StoryDayPlan> storyDayPlans;

}
