package com.twitfetch;

import com.twitfetch.entity.Timeline;
import com.twitfetch.service.TimelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.Status;
import twitter4j.Twitter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class TwitterFetchApplication implements CommandLineRunner {

	@Autowired
	private Twitter twitter;

	@Autowired
	private TimelineService timelineService;

	public static void main(String[] args) {
		SpringApplication.run(TwitterFetchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start Twitter Fetch timeline..");

		List<Status> statusList = twitter.getUserTimeline("komplaindaring").stream()
				.collect(Collectors.toList());

		log.info("there are {} timeline", statusList.size());

		for (Status status : statusList) {
			Timeline timeline = Timeline.builder()
					.id(status.getId())
					.createdDate(status.getCreatedAt())
					.name(status.getUser().getName())
					.tweet(status.getText())
					.build();
		}

		log.info("Finish fetch timeline");
	}
}
