package net.sprinboot.consumer.db.service;

import net.sprinboot.consumer.db.entity.WikiMediaData;
import net.sprinboot.consumer.db.repository.WikiMediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {
    @Autowired
   private WikiMediaDataRepository wikiMediaDataRepository;
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    private static  final Logger LOGGER= LoggerFactory.getLogger(kafkaConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void  consume(String message){
        LOGGER.info(String.format(" Message Received -->  %s",message));
        WikiMediaData wikiMediaData=new WikiMediaData();
        wikiMediaData.setWikimediaData(message);
        wikiMediaDataRepository.save(wikiMediaData);

    }

}
