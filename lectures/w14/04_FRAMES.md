# Ramki WebSocket i wymiana wiadomości


## 1. Wprowadzenie

Po zakończeniu handshake’u HTTP przestaje obowiązywać, a komunikacja pomiędzy klientem i serwerem odbywa się za pomocą **ramek WebSocket**.

Ramka to najmniejsza jednostka danych przesyłana przez WebSocket – odpowiednik „wiadomości”, ale na poziomie protokołu.

---

## 2. Struktura ramki WebSocket

Każda ramka WebSocket składa się logicznie z kilku elementów:

* **opcode** – określa typ ramki

  * `TEXT`
  * `BINARY`
  * `PING`
  * `PONG`
  * `CLOSE`

* **payload length** – długość danych

* **mask**

  * obowiązkowa **dla ramek wysyłanych przez klienta**
  * chroni przed niektórymi atakami (np. cache poisoning)

* **payload** – właściwa treść wiadomości

> 📌 Szczegółowa binarna struktura ramek jest zwykle ukryta przez biblioteki – programista pracuje na poziomie wiadomości, nie bitów.

---

## 3. Rodzaje wiadomości WebSocket

### 3.1 TEXT

* najczęściej używany typ wiadomości
* payload to tekst (UTF-8)
* bardzo często zawiera **JSON**

**Przykład wiadomości JSON lecącej przez WebSocket:**

```json
{
  "type": "chat_message",
  "user": "alice",
  "message": "Cześć wszystkim!"
}
```

Z punktu widzenia aplikacji:

* nie ma request / response
* to po prostu wiadomość wysłana „w eter”

---

### 3.2 BINARY

* payload to dane binarne
* używane np. do:

  * audio / video
  * plików
  * danych w formatach binarnych (np. protobuf)

---

### 3.3 CONTROL

Ramki kontrolne służą do zarządzania połączeniem.

#### PING / PONG

* mechanizm utrzymania połączenia
* sprawdzanie, czy druga strona nadal żyje
* PING → PONG

> Często obsługiwane automatycznie przez biblioteki WebSocket.

#### CLOSE

* inicjuje zamknięcie połączenia
* może zawierać kod i powód zamknięcia
* po wymianie ramek CLOSE połączenie TCP jest zamykane

---

## 4. Wymiana wiadomości – kluczowa zmiana mentalna

### HTTP:

```
request → response
```

### WebSocket:

```
message → message → message → ...
```

* brak sztywnego schematu
* obie strony są **równorzędne**
* serwer może wysłać wiadomość **w dowolnym momencie**

---

