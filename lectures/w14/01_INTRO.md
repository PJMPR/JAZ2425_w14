# Wprowadzenie – problem, który rozwiązują WebSockety

## 1. Kontekst

Współczesne aplikacje webowe coraz częściej wymagają **komunikacji w czasie rzeczywistym** (realtime). Użytkownicy oczekują natychmiastowych reakcji systemu, bez konieczności ręcznego odświeżania strony.

Aby zrozumieć, dlaczego powstały WebSockety, warto najpierw przyjrzeć się klasycznemu modelowi komunikacji w sieci.

---

## 2. Klasyczny model HTTP

### Schemat komunikacji

```
request  →  response
```

### Cechy modelu HTTP

* komunikację **zawsze inicjuje klient** (np. przeglądarka)
* serwer **nie może sam wysłać danych**, jeśli klient o nie nie poprosi
* każde zapytanie to osobne połączenie (lub logiczna transakcja)
* model dobrze sprawdza się dla:

  * stron informacyjnych
  * formularzy
  * klasycznych aplikacji CRUD

---

## 3. Problemy przy próbie realizacji realtime w HTTP

Gdy chcemy uzyskać dane „na bieżąco”, klasyczny HTTP zaczyna być niewystarczający.

### 3.1 Polling

* klient cyklicznie wysyła zapytania (np. co 1–5 sekund)
* pytanie w stylu: „Czy są nowe dane?”

**Wady:**

* duża liczba zapytań
* większość odpowiedzi zawiera brak zmian
* niepotrzebne obciążenie serwera i sieci

---

### 3.2 Long polling

* klient wysyła zapytanie
* serwer **czeka**, aż pojawią się nowe dane
* po odpowiedzi klient natychmiast wysyła kolejne zapytanie

**Wady:**

* bardziej skomplikowana implementacja
* długotrwałe otwarte połączenia
* nadal opóźnienia i narzut protokołu HTTP

---

### 3.3 Główne problemy podejścia HTTP

* **opóźnienia** (brak natychmiastowości)
* **nieefektywność** (dużo pustych odpowiedzi)
* trudności w skalowaniu
* brak naturalnej komunikacji dwukierunkowej

---

## 4. Przykłady systemów realtime

Są obszary, w których powyższe problemy stają się krytyczne:

* **Chat**
  wiadomości muszą pojawiać się natychmiast u drugiego użytkownika

* **Notyfikacje**
  alerty, statusy, powiadomienia systemowe

* **Giełda / trading**
  zmiany cen w czasie rzeczywistym

* **Gry online**
  synchronizacja stanu gry, ruchów graczy, zdarzeń

---

## 5. Płynne przejście do WebSocketów

Te ograniczenia klasycznego HTTP doprowadziły do powstania technologii, która:

* utrzymuje **stałe połączenie**
* umożliwia **dwukierunkową komunikację**
* pozwala serwerowi wysyłać dane **w dowolnym momencie**

➡️ **WebSockety** odpowiadają właśnie na te potrzeby i stanowią fundament nowoczesnych aplikacji realtime.

---

