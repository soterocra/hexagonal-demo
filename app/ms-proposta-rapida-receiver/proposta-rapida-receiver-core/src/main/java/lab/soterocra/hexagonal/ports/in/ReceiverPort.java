package lab.soterocra.hexagonal.ports.in;

import lab.soterocra.hexagonal.domain.model.Message;

public interface ReceiverPort {
    void hook(Message message) throws Exception;
}
