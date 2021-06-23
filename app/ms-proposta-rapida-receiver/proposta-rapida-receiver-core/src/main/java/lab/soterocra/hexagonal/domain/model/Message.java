package lab.soterocra.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String userId;
    private String messageId;
    private String message;
    private String channel;
    private Date receivedDate;

    public boolean isAValidMessage() {
        return  Objects.nonNull(userId) &&
                Objects.nonNull(messageId) &&
                Objects.nonNull(message) &&
                Objects.nonNull(channel);
    }
}
