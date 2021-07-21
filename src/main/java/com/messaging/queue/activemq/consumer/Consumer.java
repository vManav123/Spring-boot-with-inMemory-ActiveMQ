package com.messaging.queue.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @JmsListener(destination = "inmemory.queue")
    public void listener(String message){
        log.info("Received Message : "+message + " ✔");
    }
}
