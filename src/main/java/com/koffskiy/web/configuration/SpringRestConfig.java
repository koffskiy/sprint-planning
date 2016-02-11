package com.koffskiy.web.configuration;

import com.koffskiy.data.Sprint;
import com.koffskiy.data.Story;
import com.koffskiy.data.StoryPlan;
import com.koffskiy.data.TeamMember;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringRestConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Sprint.class, Story.class, TeamMember.class, StoryPlan.class);
	}
}
