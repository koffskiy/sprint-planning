package com.koffskiy.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class StoryDayPlan {

    @Id
    @GeneratedValue
    private Long id;
    private Integer dayNumber;

    @ManyToMany
    private List<TeamMember> members;
}
