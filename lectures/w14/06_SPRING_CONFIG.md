# Konfiguracja WebSocket + STOMP w Spring – omówienie krok po kroku


---

## 1. Rola tej konfiguracji w aplikacji

Klasa `WebSocketConfiguration`:

* włącza obsługę **WebSocketów w Springu**,
* konfiguruje **STOMP jako protokół wiadomości**,
* definiuje:

  * punkt wejścia dla klientów (endpoint),
  * sposób routingu wiadomości,
  * prosty broker wiadomości (pub/sub).

Ta klasa jest **mostem pomiędzy frontendem a backendem realtime**.

---

## 2. Adnotacje konfiguracyjne

```java
@Configuration
@EnableWebSocketMessageBroker
```

### `@Configuration`

* oznacza klasę jako **konfigurację Springa**,
* Spring odczyta ją przy starcie aplikacji.

### `@EnableWebSocketMessageBroker`

* włącza obsługę:

  * WebSocketów,
  * STOMP-a,
  * brokera wiadomości,
* aktywuje infrastrukturę *publish / subscribe*.

➡️ Bez tej adnotacji STOMP w Springu **nie działa**.

---

## 3. Implementacja `WebSocketMessageBrokerConfigurer`

```java
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer
```

Dzięki temu interfejsowi możemy:

* zarejestrować endpointy WebSocket,
* skonfigurować broker wiadomości,
* ustawić prefiksy routingu.

---

## 4. Endpoint WebSocket – `registerStompEndpoints`

```java
@Override
public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws");
}
```

### Co to oznacza?

* `/ws` to **adres WebSocket**, z którym łączy się frontend
* handshake wygląda np. tak:

```
ws://localhost:8080/ws
```

lub (z HTTPS):

```
wss://example.com/ws
```

➡️ To **jedyny punkt**, w którym klient zestawia połączenie WebSocket.

---

## 5. Konfiguracja brokera wiadomości – `configureMessageBroker`

Ta metoda definiuje **jak wiadomości są routowane** w aplikacji.

---

### 5.1 `setApplicationDestinationPrefixes("/app")`

```java
registry.setApplicationDestinationPrefixes("/app");
```

#### Co robi `/app`?

* wszystkie wiadomości wysyłane przez klienta na adresy:

```
/app/...
```

* trafiają do:

  * metod kontrolerów oznaczonych `@MessageMapping`

#### Przykład

Frontend wysyła:

```
SEND /app/chat
```

Backend:

```java
@MessageMapping("/chat")
public void handleChat(...) { }
```

➡️ `/app` **nigdy nie jest subskrybowane przez klienta**.

---

### 5.2 `enableSimpleBroker("/queue")`

```java
registry.enableSimpleBroker("/queue");
```

#### Co to jest simple broker?

* wbudowany, prosty broker STOMP w Springu
* obsługuje:

  * subskrypcje
  * routing wiadomości
  * broadcast do wielu klientów

#### Co oznacza `/queue`?

* klienci **subskrybują** destynacje:

```
/queue/...
```

* serwer **wysyła** tam wiadomości

#### Przykład

Frontend:

```
SUBSCRIBE /queue/messages
```

Backend:

```java
messagingTemplate.convertAndSend("/queue/messages", payload);
```

➡️ Wszystkie subskrybujące klienty otrzymają wiadomość.

---

## 6. Pełny przepływ wiadomości (mentalny model)

1. Klient łączy się z `/ws` (WebSocket handshake)
2. Klient wysyła wiadomość do `/app/...`
3. Spring:

   * odbiera wiadomość
   * uruchamia metodę `@MessageMapping`
4. Serwer publikuje wiadomość do `/queue/...`
5. Broker rozsyła ją do subskrybentów

---

## 7. Najważniejsze rzeczy do zapamiętania 🎓

* `/ws` → **endpoint WebSocket**
* `/app` → **wiadomości do backendu**
* `/queue` → **wiadomości do klientów**
* WebSocket = transport
* STOMP = semantyka + routing + subskrypcje

> **Spring + STOMP zamienia WebSocket z „rury” w pełnoprawny system komunikacji.**

