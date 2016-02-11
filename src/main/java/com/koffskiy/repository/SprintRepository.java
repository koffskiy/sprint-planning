package com.koffskiy.repository;

import com.koffskiy.data.Sprint;
import com.koffskiy.data.Story;
import org.springframework.data.repository.CrudRepository;

public interface SprintRepository extends CrudRepository<Sprint, Long> {
}
