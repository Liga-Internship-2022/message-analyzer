package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.api.RabbitSenderService;
import liga.medical.service.LoggingService;
import lombok.RequiredArgsConstructor;
import model.RabbitMessageDto;
import model.SystemType;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitSenderServiceImpl implements RabbitSenderService {

    private final LoggingService loggingService;

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(RabbitMessageDto messageDto, String queue) throws JsonProcessingException {
        String messageAsJson = objectMapper.writeValueAsString(messageDto);
        amqpTemplate.convertAndSend(queue, messageAsJson);

        loggingService.logQueueMessageSending(messageDto, queue, SystemType.MESSAGE_ANALYZER);
    }
}
