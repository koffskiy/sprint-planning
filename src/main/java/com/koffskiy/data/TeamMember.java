package com.koffskiy.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class TeamMember {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String icon;

    public TeamMember(String name, String icon, Long id) {
        this.name = name;
        this.icon = icon;
        this.id = id;
    }


}
