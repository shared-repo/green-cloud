package com.demoweb.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@RestController
public class SseController {

    @GetMapping(path = { "/stream-sse" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvent() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return Flux.interval(Duration.ofSeconds(3))
                .map(sequence -> "SSE event - " + sdf.format(new Date()));
    }
}
