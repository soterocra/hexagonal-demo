package lab.soterocra.hexagonal.mspropostarapidadatainteraction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String userId;
    private String cpf;
    private String name;
    private String email;
    private String phone;
    private String role;

}
