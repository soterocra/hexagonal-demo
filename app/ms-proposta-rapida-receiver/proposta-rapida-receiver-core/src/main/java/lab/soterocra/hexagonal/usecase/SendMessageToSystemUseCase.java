package lab.soterocra.hexagonal.usecase;

import lab.soterocra.hexagonal.domain.exception.InvalidMessageException;
import lab.soterocra.hexagonal.domain.model.Message;
import lab.soterocra.hexagonal.ports.in.ReceiverPort;
import lab.soterocra.hexagonal.ports.out.PostMessagePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SendMessageToSystemUseCase implements ReceiverPort {

    private final PostMessagePort postMessagePort;

    @Override
    public void hook(Message message) throws Exception {
        log.info("Mensagem recebida na classe de negócio.");
        Optional.ofNullable(message).orElseThrow(() -> new InvalidMessageException("Mensagem Nula, processo invalido."));
        if (!message.isAValidMessage()) throw new InvalidMessageException("Mensagem não é válida, verifique se todos os campos estão preenchidos: " + message);

        buildCompleteMessage(message);

        postMessagePort.sendMessage(message);
    }

    public void buildCompleteMessage(Message message) {
        message.setReceivedDate(new Date());
    }
}
