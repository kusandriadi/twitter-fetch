package com.twitfetch.service.impl;

import com.twitfetch.entity.Timeline;
import com.twitfetch.repository.TimelineRepository;
import com.twitfetch.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimelineServiceImpl implements TimelineService {

    @Autowired
    private TimelineRepository timelineRepository;

    @Override
    public void save(Timeline timeline) {
        timelineRepository.save(timeline);
    }
}
