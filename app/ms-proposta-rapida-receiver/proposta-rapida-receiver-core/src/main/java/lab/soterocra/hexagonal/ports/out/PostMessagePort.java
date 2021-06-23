package lab.soterocra.hexagonal.ports.out;

import lab.soterocra.hexagonal.domain.model.Message;

public interface PostMessagePort {
    void sendMessage(Message message);
}
