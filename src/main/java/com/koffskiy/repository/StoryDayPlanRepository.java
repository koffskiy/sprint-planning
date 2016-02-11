package com.koffskiy.repository;

import com.koffskiy.data.StoryDayPlan;
import com.koffskiy.data.StoryPlan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sergii on 11.12.2015.
 */
public interface StoryDayPlanRepository extends CrudRepository<StoryDayPlan, Long> {
}
