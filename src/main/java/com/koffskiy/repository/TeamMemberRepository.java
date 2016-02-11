package com.koffskiy.repository;

import com.koffskiy.data.TeamMember;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sergii on 11.12.2015.
 */
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
}
