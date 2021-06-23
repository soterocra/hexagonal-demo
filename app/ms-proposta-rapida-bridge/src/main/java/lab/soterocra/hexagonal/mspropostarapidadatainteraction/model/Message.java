package lab.soterocra.hexagonal.mspropostarapidadatainteraction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    private String userId;
    private String messageId;
    private String message;
    private String channel;
    private Date receivedDate;

}
