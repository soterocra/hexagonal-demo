package lab.soterocra.hexagonal.mspropostarapidadatainteraction.service;

import lab.soterocra.hexagonal.mspropostarapidadatainteraction.entity.MessageEntity;
import lab.soterocra.hexagonal.mspropostarapidadatainteraction.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public MessageEntity save(MessageEntity entity) {
        return repository.save(entity);
    }
}
