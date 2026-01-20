# Czym jest WebSocket – podstawy protokołu

## 1. Wprowadzenie

WebSocket to technologia zaprojektowana z myślą o **komunikacji w czasie rzeczywistym** pomiędzy klientem a serwerem. Powstała jako odpowiedź na ograniczenia klasycznego modelu HTTP, szczególnie w kontekście aplikacji wymagających natychmiastowej wymiany danych.

---

## 2. Kluczowe cechy WebSocket

### 2.1 Protokół aplikacyjny (RFC 6455)

* WebSocket jest **pełnoprawnym protokołem aplikacyjnym**
* jego specyfikacja została opisana w **RFC 6455**
* posiada jasno zdefiniowane zasady nawiązywania połączenia, przesyłania danych i zamykania sesji

---

### 2.2 Działa nad TCP

* WebSocket wykorzystuje **połączenie TCP**
* zapewnia:

  * niezawodność
  * zachowanie kolejności pakietów
  * kontrolę przeciążeń
* połączenie TCP jest utrzymywane przez cały czas trwania sesji WebSocket

---

### 2.3 Full‑duplex (dwukierunkowa komunikacja)

* zarówno **klient**, jak i **serwer** mogą wysyłać dane:

  * w dowolnym momencie
  * niezależnie od siebie
* brak konieczności oczekiwania na zapytanie (request), aby wysłać dane

➡️ To kluczowa różnica w porównaniu do HTTP.

---

### 2.4 Jedno połączenie → wiele wiadomości

* po zestawieniu połączenia:

  * nie jest ono zamykane po jednej odpowiedzi
  * w ramach jednego połączenia przesyłanych jest **wiele wiadomości**
* brak kosztu ponownego zestawiania połączenia

---

### 2.5 Niskie opóźnienia

* brak ciągłego przesyłania nagłówków HTTP
* lekkie ramki danych
* możliwość natychmiastowego przesyłania informacji

Efekt: **minimalne opóźnienia**, idealne dla systemów realtime.

---

## 3. Porównanie HTTP vs WebSocket

| HTTP                   | WebSocket                          |
| ---------------------- | ---------------------------------- |
| request → response     | komunikacja dwukierunkowa          |
| stateless              | połączenie stanowe                 |
| duży narzut nagłówków  | lekka ramka danych                 |
| klient zawsze inicjuje | klient **i** serwer mogą inicjować |

---


