package com.smallstudy.event;

import com.smallstudy.service.ParseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitialize {

    private final ApplicationEventPublisher eventPublisher;
    private final ParseService parseService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws IOException {
        parseService.parsingCategory();
        parseService.parsingRegion();
        eventPublisher.publishEvent(new DataInitializedEvent());
    }

}
