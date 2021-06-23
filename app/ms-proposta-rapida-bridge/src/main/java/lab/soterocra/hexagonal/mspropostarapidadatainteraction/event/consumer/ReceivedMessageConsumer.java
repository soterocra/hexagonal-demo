package lab.soterocra.hexagonal.mspropostarapidadatainteraction.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab.soterocra.hexagonal.mspropostarapidadatainteraction.model.Message;
import lab.soterocra.hexagonal.mspropostarapidadatainteraction.service.BridgeMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReceivedMessageConsumer {

    private final BridgeMessageService messageService;
    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(id = "ms-proposta-rapida-bridge", topics = "TOPIC_RECEIVED_MESSAGES")
    private void consumer(String message) throws JsonProcessingException {
        log.info("Mensagem Recebida: {}", message);

        Message entity = mapper.readValue(message, Message.class);
        messageService.send(entity);
    }

}
