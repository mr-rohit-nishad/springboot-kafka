package net.springboot.kafka.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {
    private static  final Logger LOGGER= LoggerFactory.getLogger(WikimediaChangesProducer.class);
    @Value("${wikimedia.url}")
    private String wikiMediaUrl;
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage() throws InterruptedException {

        EventHandler eventHandler=new WikiMediaChangesHandler(kafkaTemplate,topicName);
        EventSource.Builder builder=new EventSource.Builder(eventHandler, URI.create(wikiMediaUrl));
        EventSource eventSource=builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
       // to read real time data from wikimedia
     }
}
