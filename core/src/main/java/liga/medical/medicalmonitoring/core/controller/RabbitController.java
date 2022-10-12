package liga.medical.medicalmonitoring.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmonitoring.core.api.RabbitSenderService;
import liga.medical.medicalmonitoring.core.config.RabbitConfig;
import liga.medical.medicalmonitoring.core.model.RabbitMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitSenderService rabbitSenderService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody RabbitMessageDto message) throws JsonProcessingException {
        rabbitSenderService.sendMessage(message, RabbitConfig.ROUTER_QUEUE_NAME);
    }
}
