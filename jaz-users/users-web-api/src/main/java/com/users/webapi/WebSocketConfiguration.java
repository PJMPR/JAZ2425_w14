package com.users.webapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * WebSockety to protokół komunikacyjny,
 * który umożliwia dwukierunkową,
 * pełnodupleksową komunikację w czasie rzeczywistym między klientem (np. przeglądarką)
 * a serwerem za pośrednictwem jednego, trwałego połączenia TCP.
 *
 * Jak działają WebSockety?
 * 1. Handshake HTTP:
 *
 *      Połączenie WebSocket rozpoczyna się jako zwykłe żądanie HTTP (tzw. handshake)
 *      z nagłówkiem Upgrade,
 *      który informuje serwer o chęci nawiązania połączenia WebSocket.
 *      Przykładowy nagłówek:
 *
 *          GET /ws HTTP/1.1
 *          Host: example.com
 *          Upgrade: websocket
 *          Connection: Upgrade
 * 2.Trwałe połączenie:
 *
 *      Po zaakceptowaniu przez serwer,
 *      połączenie jest "promowane" do WebSocket i pozostaje otwarte.
 *      Wysyłanie i odbieranie danych:
 *
 * 3. Po nawiązaniu połączenia,
 *      dane mogą być przesyłane w obu kierunkach bez potrzeby dodatkowych zapytań HTTP.
 *
 * Przykłady zastosowania WebSocketów:
 *  Aplikacje czasu rzeczywistego:
 *      Czat (np. komunikatory internetowe).
 *      Powiadomienia w czasie rzeczywistym.
 *      Gry online.
 *  Systemy monitorowania:
 *      Aktualizacja danych w czasie rzeczywistym (np. monitorowanie systemów, wykresy).
 *  Aplikacje IoT:
 *      Sterowanie urządzeniami w czasie rzeczywistym.
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * STOMP (ang. Simple Text Oriented Messaging Protocol) to prosty,
     * tekstowy protokół komunikacyjny,
     * który umożliwia wymianę wiadomości w systemach opartych na modelu publish/subscribe (pub/sub)
     * oraz point-to-point.
     * Jest używany głównie w aplikacjach komunikujących się za pomocą WebSocketów.

     Kluczowe cechy STOMP:
     Prostota i czytelność:

        Komunikaty w STOMP są wysyłane w formie ramek tekstowych.
        Każda ramka zawiera komendę (np. SEND, SUBSCRIBE)
        i opcjonalne nagłówki oraz ciało wiadomości.
     przykład wiadomości:

     ----------------------------
     - SEND                     -
     - destination:/topic/news  -
     - content-type:text/plain  -
     -                          -
     - Hello, World!            -
     ----------------------------

     Model publikacji i subskrypcji (pub/sub):

        Klienci mogą publikować wiadomości na określone destynacje, np. /topic/news.
        Inni klienci subskrybują te destynacje, aby odbierać wiadomości.

     Abstrakcja nad brokerami wiadomości:

        STOMP nie definiuje, jak broker wiadomości implementuje swoją logikę.
        Może działać z różnymi brokerami, np. ActiveMQ, RabbitMQ, czy wewnętrznym brokerem Spring.

     Wsparcie dla protokołu WebSocket:

        STOMP jest często używany razem z WebSocketami w aplikacjach webowych,
        umożliwiając efektywną komunikację w czasie rzeczywistym.

     Typowe komendy STOMP:
        CONNECT: Inicjalizacja połączenia z brokerem STOMP.
        SEND: Wysyłanie wiadomości do określonego miejsca docelowego.
        SUBSCRIBE: Subskrybowanie wiadomości z określonego miejsca docelowego.
        UNSUBSCRIBE: Rezygnacja z subskrypcji.
        DISCONNECT: Zamknięcie połączenia.

     * registerStompEndpoints: Określa endpoint /messages, do którego będą się łączyć klienci (frontend).
     * @param registry
     */

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws");
    }

    /**
     * configureMessageBroker: Ustawia prefixy dla kierowania wiadomości do odpowiednich miejsc.
     * Na przykład, wszystkie wiadomości wysyłane do ścieżek zaczynających się od /app
     * będą traktowane jako wiadomości do aplikacji.
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        /**
         * Ta linia określa prefiks dla destynacji, do których wiadomości są kierowane z klienta (np. przeglądarki) do serwera.
         *
         * Kiedy klient wysyła wiadomość do serwera,
         * używa on destynacji z tym prefiksem.
         * Na przykład, jeśli klient chce wysłać wiadomość do aplikacji,
         * może użyć destynacji takiej jak /app/chat.
         * Ta konfiguracja pozwala serwerowi na rozróżnienie między różnymi rodzajami akcji,
         * takimi jak wysyłanie wiadomości do aplikacji (/app)
         * a subskrybowanie tematów w brokera wiadomości (/topic lub /queue).
         */
        registry.setApplicationDestinationPrefixes("/app");

        /**
         * Ta linia konfiguruje prosty wewnętrzny broker wiadomości, który pozwala na przesyłanie wiadomości od serwera do klienta.
         *
         * Broker wiadomości używa tego prefiksu (/queue) do zarządzania subskrypcjami i przesyłaniem wiadomości do subskrybentów.
         * Na przykład, jeśli kilku użytkowników subskrybuje temat /queue/messages,
         * to każda wiadomość wysłana przez serwer do tego tematu zostanie automatycznie przekazana wszystkim subskrybentom.
         * Jest to szczególnie przydatne w aplikacjach chatu,
         * gdzie serwer musi przesyłać wiadomości do wszystkich klientów,
         * którzy subskrybowali dany temat a raczej kolejkę.
         */
        registry.enableSimpleBroker("/queue");
    }
}
