package lab.soterocra.hexagonal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab.soterocra.hexagonal.domain.model.Message;
import lab.soterocra.hexagonal.ports.out.PostMessagePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
public class Producer implements PostMessagePort {

    private static final String TOPIC = "TOPIC_RECEIVED_MESSAGES";
    private final ObjectMapper mapper = new ObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(Message message) {
        log.info("Mensagem sendo produzida para o Kafka: {}", message);
        try {
            this.kafkaTemplate.send(TOPIC, mapper.writeValueAsString(message));
            log.info("Mensagem enviada.");
        } catch (JsonProcessingException e) {
            log.error("Falha ao produzir a mensagem.", e);
        }
    }
}
