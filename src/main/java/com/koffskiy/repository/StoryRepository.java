package com.koffskiy.repository;

import com.koffskiy.data.Story;
import com.koffskiy.data.StoryPlan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sergii on 11.12.2015.
 */
public interface StoryRepository extends CrudRepository<Story, Long> {
}
