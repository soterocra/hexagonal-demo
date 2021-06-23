package lab.soterocra.hexagonal;

import lab.soterocra.hexagonal.domain.model.Message;
import lab.soterocra.hexagonal.ports.in.ReceiverPort;
import lab.soterocra.hexagonal.ports.out.PostMessagePort;
import lab.soterocra.hexagonal.usecase.SendMessageToSystemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@ComponentScan(basePackages = "lab.soterocra.hexagonal")
@RequiredArgsConstructor
public class PropostaRapidaReceiverConfiguration {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    public ReceiverPort receiverPort() {
        return new SendMessageToSystemUseCase(postMessagePort());
    }

    @Bean
    @Primary
    public PostMessagePort postMessagePort() {
        return new Producer(kafkaTemplate);
    }

    @Bean
    public PostMessagePort postMessagePortPrint() {
        return new PostMessagePort() {
            @Override
            public void sendMessage(Message message) {
                System.out.println("Minha mensagem: " + message.toString());
            }
        };
    }
}
