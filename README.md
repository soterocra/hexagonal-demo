## Narrativa de Negócios

Márcia ocupa um cargo de Gerente Estadual em uma empresa que vende cosméticos. Márcia nos enviou um e-mail com a seguinte solicitação:

> Eu, enquanto gerente estadual de uma grande empresa de cosméticos, preciso de um canal rápido e fácil em que seja possível que os meus revendedores municipais me enviem propostas de compras com desconto solicitados por nossos clientes.

Além disso, ela descreveu os seguintes requisitos:

> Eu preciso:
> * Conseguir enviar comunicados
> * Conseguir aprovar ou negar uma proposta de compra abaixo do preço tabelado
> 
> Os meus revendedores precisam:
> * Conseguir ler meus comunicados
> * Submeter propostas para aprovação

Para finalizar, Márcia acrescentou no e-mail o seguinte comentário: 

> Hoje já fazemos essa comunicação por chat via whatsapp ou telegram, gostaria que continuasse assim, porém um pouco mais automático.

Bem, agora que temos os requisitos do time de negócio, vamos trabalhar na nossa solução.

## Pensando na Solução

Como Márcia e seu time já utilizam aplicativos de chat para se comunicar, podemos nos aproveitar disso, mais especificamente do Telegram que possui formas gratuitas de integração.

Bem, mas como usar o telegram para resolver essa situação?

**Vamos utilizar BOTs!!**

Isso mesmo, um robô (ou mais) que fazem a comunicação entre o time, utilizando o próprio Telegram.

Não sabe o que são BOTs? Dê uma olhadinha no site do telegram: https://core.telegram.org/bots

Em algum momento você já deve ter utilizado, são aqueles robôs de comunicação.

### Como funciona nossa solução?

Utilizando o Hexagono:


Gerando o nosso projeto...
mvn archetype:generate -DgroupId=com.companyname.blogger -DartifactId=blogger


mvn archetype:generate -DgroupId=lab.soterocra.hexagonal -DartifactId=proposta-rapida -DinteractiveMode=false

mvn archetype:generate -DgroupId=lab.soterocra.hexagonal -DartifactId=proposta-rapida-core -DinteractiveMode=false

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.7.2</version>
    <scope>test</scope>
</dependency>
```
mvn archetype:generate -DgroupId=lab.soterocra.hexagonal -DartifactId=proposta-rapida-receiver-app -DinteractiveMode=false
mvn archetype:generate -DgroupId=lab.soterocra.hexagonal -DartifactId=./adapters/proposta-rapida-receiver-webhook-telegram -DinteractiveMode=false

Implementação Inicial das Coonfigurações:

```java
package lab.soterocra.hexagonal;

import lab.soterocra.hexagonal.domain.model.Message;
import lab.soterocra.hexagonal.ports.in.ReceiverPort;
import lab.soterocra.hexagonal.ports.out.PostMessagePort;
import lab.soterocra.hexagonal.usecase.SendMessageToSystemUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "lab.soterocra.hexagonal")
public class PropostaRapidaConfiguration {

    @Bean
    public ReceiverPort receiverPort() {
        return new SendMessageToSystemUseCase(postMessagePort());
    }

    @Bean
    public PostMessagePort postMessagePort() {
        return new PostMessagePort() {
            @Override
            public void sendMessage(Message message) {
                System.out.println("Minha mensagem: " + message.toString());
            }
        };
    }

}
```

mvn archetype:generate -DgroupId=lab.soterocra.hexagonal -DartifactId=/adapters/proposta-rapida-receiver-kafka -DinteractiveMode=false



