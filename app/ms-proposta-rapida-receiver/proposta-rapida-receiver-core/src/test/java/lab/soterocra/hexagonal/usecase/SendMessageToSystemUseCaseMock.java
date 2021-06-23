package lab.soterocra.hexagonal.usecase;

import lab.soterocra.hexagonal.domain.model.Message;

public class SendMessageToSystemUseCaseMock {

    public static Message getSuccessMessage() {
        return new Message("829182", "12938109", "Essa é uma mensagem de testes", "telegram", null);
    }

    public static Message getNullFieldsMessage() {
        return new Message();
    }

    public static Message getMessageWithUserIdNull() {
        Message message = getSuccessMessage();
        message.setUserId(null);
        return message;
    }

    public static Message getMessageWithMessageIdNull() {
        Message message = getSuccessMessage();
        message.setMessageId(null);
        return message;
    }

    public static Message getMessageWithMessageNull() {
        Message message = getSuccessMessage();
        message.setMessage(null);
        return message;
    }

    public static Message getMessageWithChannelNull() {
        Message message = getSuccessMessage();
        message.setMessage(null);
        return message;
    }
}
