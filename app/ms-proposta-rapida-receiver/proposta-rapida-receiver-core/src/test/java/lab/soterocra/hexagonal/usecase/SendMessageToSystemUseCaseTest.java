package lab.soterocra.hexagonal.usecase;

import lab.soterocra.hexagonal.domain.exception.InvalidMessageException;
import lab.soterocra.hexagonal.domain.model.Message;
import lab.soterocra.hexagonal.ports.out.PostMessagePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SendMessageToSystemUseCaseTest {

    @Mock private PostMessagePort postMessagePort;
    @InjectMocks private SendMessageToSystemUseCase sendMessageToSystemUseCase;

    @BeforeEach
    void setUp() {
    }

    @Test
    void hook_aoReceberMensagemTotalmentePreenchidaExecutaOFluxoComSucesso() throws Exception {
        Message message = SendMessageToSystemUseCaseMock.getSuccessMessage();
        sendMessageToSystemUseCase.hook(message);
        Mockito.verify(postMessagePort, Mockito.times(1)).sendMessage(message);
    }

    @Test
    void hook_aoReceberMensagemNulaLancaExcecao() {
        InvalidMessageException t = Assertions.assertThrows(InvalidMessageException.class, () -> sendMessageToSystemUseCase.hook(null));
        Assertions.assertEquals("Mensagem Nula, processo invalido.", t.getMessage());
    }

    @Test
    void hook_aoReceberMensagemComTodasColunasNulasLancaExcecao() {
        Message message = SendMessageToSystemUseCaseMock.getNullFieldsMessage();
        InvalidMessageException t = Assertions.assertThrows(InvalidMessageException.class, () -> sendMessageToSystemUseCase.hook(message));
        Assertions.assertEquals("Mensagem não é válida, verifique se todos os campos estão preenchidos: " + message, t.getMessage());
    }

    @Test
    void hook_aoReceberMensagemComUserIdNuloLancaExcecao() {
        Message message = SendMessageToSystemUseCaseMock.getMessageWithUserIdNull();
        InvalidMessageException t = Assertions.assertThrows(InvalidMessageException.class, () -> sendMessageToSystemUseCase.hook(message));
        Assertions.assertEquals("Mensagem não é válida, verifique se todos os campos estão preenchidos: " + message, t.getMessage());
    }

    @Test
    void hook_aoReceberMensagemComMessageIdNuloLancaExcecao() {
        Message message = SendMessageToSystemUseCaseMock.getMessageWithMessageIdNull();
        InvalidMessageException t = Assertions.assertThrows(InvalidMessageException.class, () -> sendMessageToSystemUseCase.hook(message));
        Assertions.assertEquals("Mensagem não é válida, verifique se todos os campos estão preenchidos: " + message, t.getMessage());
    }

    @Test
    void hook_aoReceberMensagemComMessageNuloLancaExcecao() {
        Message message = SendMessageToSystemUseCaseMock.getMessageWithMessageNull();
        InvalidMessageException t = Assertions.assertThrows(InvalidMessageException.class, () -> sendMessageToSystemUseCase.hook(message));
        Assertions.assertEquals("Mensagem não é válida, verifique se todos os campos estão preenchidos: " + message, t.getMessage());
    }

    @Test
    void hook_devePreencherDataEHoradaMensagem() throws Exception {
        Message message = SendMessageToSystemUseCaseMock.getSuccessMessage();
        Assertions.assertNull(message.getReceivedDate());
        sendMessageToSystemUseCase.hook(message);
        Assertions.assertNotNull(message.getReceivedDate());
    }
}