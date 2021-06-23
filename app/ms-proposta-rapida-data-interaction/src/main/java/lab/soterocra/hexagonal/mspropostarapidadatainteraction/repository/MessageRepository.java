package lab.soterocra.hexagonal.mspropostarapidadatainteraction.repository;

import lab.soterocra.hexagonal.mspropostarapidadatainteraction.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
