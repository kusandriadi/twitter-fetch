package com.twitfetch.stream;

import com.twitfetch.entity.Timeline;
import com.twitfetch.service.TimelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TwitterStream implements CommandLineRunner {

    @Autowired
    private Twitter twitter;

    @Autowired
    private TimelineService timelineService;

    @Value("${twitter.user}")
    private String twitterUser;

    @Value("${twitter.dateFrom}")
    private String dateFrom;

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Twitter Fetch timeline..");

        List<Status> statusList = twitter.getUserTimeline(twitterUser).stream()
                .collect(Collectors.toList());

        log.info("there are {} timeline", statusList.size());

        Date dateFrom = convertDate();
        for (Status status : statusList) {
            if (dateFrom != null) {
                if (dateFrom.after(status.getCreatedAt())) {
                    continue;
                }
            }

            Timeline timeline = Timeline.builder()
                    .id(status.getId())
                    .createdDate(status.getCreatedAt())
                    .name(status.getUser().getName())
                    .tweet(status.getText())
                    .build();

            timelineService.save(timeline);
        }

        log.info("Finish fetch timeline");
    }

    private Date convertDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(dateFrom);
        } catch (ParseException e) {
            log.error("Failed to parse date", e);
        }

        return date;
    }
}
