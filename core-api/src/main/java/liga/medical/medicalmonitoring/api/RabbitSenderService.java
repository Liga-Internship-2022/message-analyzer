package liga.medical.medicalmonitoring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.RabbitMessageDto;

public interface RabbitSenderService {
    void sendMessage(RabbitMessageDto messageDto, String queue) throws JsonProcessingException;
}
