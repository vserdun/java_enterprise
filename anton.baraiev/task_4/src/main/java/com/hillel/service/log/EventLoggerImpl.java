package com.hillel.service.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventLoggerImpl implements EventLogger {
    @Override
    public void log(String message) {
        log.info(message);
    }
}
