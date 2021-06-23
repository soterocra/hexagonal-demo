package lab.soterocra.hexagonal.mapper;

import lab.soterocra.hexagonal.domain.model.Message;
import lab.soterocra.hexagonal.dto.TelegramDTO;

public class TelegramMapper {

    private static final String CHANNEL_TELEGRAM = "telegram";

    public Message toDomainMessage(TelegramDTO telegramMessage) {
        Message message = new Message();
        message.setMessage(telegramMessage.getMessage().getText());
        message.setMessageId(telegramMessage.getMessage().getMessageId().toString());
        message.setChannel(CHANNEL_TELEGRAM);
        message.setUserId(telegramMessage.getMessage().getChat().getId().toString());
        return message;
    }

}
