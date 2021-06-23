package lab.soterocra.hexagonal.mspropostarapidadatainteraction.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class UserEntity {

    @Id
    private String userId;
    private String cpf;
    private String name;
    private String email;
    private String phone;
    private String role;

}
