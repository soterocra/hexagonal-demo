package lab.soterocra.hexagonal.mspropostarapidadatainteraction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lab.soterocra.hexagonal.mspropostarapidadatainteraction.model.Message;
import lab.soterocra.hexagonal.mspropostarapidadatainteraction.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BridgeMessageService {

    private static final String ROLE_EMPLOYEE = "employee";
    private static final String ROLE_SUPERVISOR = "supervisor";

    @Value("${telegram.token}")
    private String telegramToken;

    private final ObjectMapper mapper = new ObjectMapper();

    public void send(Message message) {
        try {
            if (message.getMessage().equals("/start")) {
                log.info("Respondendo userId (chatId)");
                sendUserId(message.getUserId());
                return;
            }

            User user = getUserById(message.getUserId());

            if (user.getRole().equals(ROLE_EMPLOYEE)) {
                List<User> usersSupervisor = getUsersSupervisor();
                log.info("Enviando Mensagem para Gerentes.");
                for (User u : usersSupervisor) {
                    sendToSupervisorMessage(user, u.getUserId(), message);
                }
            } else if (user.getRole().equals(ROLE_SUPERVISOR)) {
                if (message.getMessage().startsWith("🔴 Negar. ID:") || message.getMessage().startsWith("🟢 Aceitar. ID:")) {
                    String userId = message.getMessage().replaceAll("\\D+","");
                    log.info("Enviando Mensagem para Revendedores.");
                    sendToEmployeeMessage(user, userId, message);
                } else {
                    List<User> usersEmployee = getUsersEmployee();
                    log.info("Enviando Mensagem para Revendedores.");
                    for (User u : usersEmployee) {
                        sendToEmployeeMessage(user, u.getUserId(), message);
                    }
                }
            }
        } catch (UnirestException e) {
            log.error("Falha ao fazer a ponte entre usuários.", e);
        }
    }

    private User getUserById(String userId) throws UnirestException {
        HttpResponse<User> response = Unirest.get("http://localhost:8083/users/" + userId).asObject(User.class);
        if (response.getStatus() == 200) return response.getBody();
        throw new RuntimeException("Não foi possível localizar o usuário.");
    }

    private List<User> getUsersSupervisor() throws UnirestException {
        HttpResponse<User[]> response = Unirest.get("http://localhost:8083/users/supervisors").asObject(User[].class);
        if (response.getStatus() == 200 && response.getBody().length > 0) return Arrays.asList(response.getBody());
        throw new RuntimeException("Não foi possível localizar os usuários supervisores.");
    }

    private List<User> getUsersEmployee() throws UnirestException {
        HttpResponse<User[]> response = Unirest.get("http://localhost:8083/users/employees").asObject(User[].class);
        if (response.getStatus() == 200 && response.getBody().length > 0) return Arrays.asList(response.getBody());
        throw new RuntimeException("Não foi possível localizar os usuários revendedores.");
    }

    private void sendToSupervisorMessage(User from, String to, Message message) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://api.telegram.org/" + telegramToken + "/sendMessage")
                .header("Content-Type", "application/json")
                .body(String.format("{\"chat_id\":%s,\"text\":\"#ID: %s\nNome: %s\nTelefone: %s\nMensagem: %s...\",\"reply_markup\":{\"one_time_keyboard\":true,\"keyboard\":[[{\"text\":\"🔴 Negar. ID:%s\"},{\"text\":\"🟢 Aceitar. ID:%s\"}]]}}", to, from.getUserId(), from.getName(), from.getPhone(), message.getMessage(), from.getUserId(), from.getUserId()))
                .asString();
    }

    private void sendToEmployeeMessage(User from, String to, Message message) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://api.telegram.org/" + telegramToken + "/sendMessage")
                .header("Content-Type", "application/json")
                .body(String.format("{\"chat_id\":%s,\"text\":\"#ID: %s\nNome: %s\nTelefone: %s\nMensagem: %s...\"}", to, from.getUserId(), from.getName(), from.getPhone(), message.getMessage()))
                .asString();
    }

    private void sendUserId(String to) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://api.telegram.org/" + telegramToken + "/sendMessage")
                .header("Content-Type", "application/json")
                .body(String.format("{\"chat_id\":%s,\"text\":\"#ID: %s\"}", to, to))
                .asString();
    }
}
