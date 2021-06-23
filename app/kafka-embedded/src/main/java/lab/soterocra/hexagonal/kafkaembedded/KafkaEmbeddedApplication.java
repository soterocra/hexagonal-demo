package lab.soterocra.hexagonal.kafkaembedded;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

@SpringBootApplication
public class KafkaEmbeddedApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaEmbeddedApplication.class, args);
	}

	@Bean
	EmbeddedKafkaBroker broker() {
		return new EmbeddedKafkaBroker(1)
				.kafkaPorts(9092)
				.brokerListProperty("spring.kafka.bootstrap-servers"); // override application property
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("so63812994").partitions(1).replicas(1).build();
	}

	@KafkaListener(id = "kafka-embedded", topics = "TOPIC_RECEIVED_MESSAGES")
	public void listen(String in) {
		System.out.println(in);
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
		return args -> {
			template.send("TOPIC_RECEIVED_MESSAGES", "Topic Online!");
		};
	}


}
