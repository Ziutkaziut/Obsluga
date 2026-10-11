# Przeróbka obsługi kliknięć – Raport

## 1. Co się zmieniło?
W pierwotnej wersji (Zadanie 2) obsługa kliknięć była realizowana za pomocą **wyrażeń lambda** (`v -> { ... }`) przypisanych bezpośrednio do przycisków lub w formie osobnej zmiennej listenera.

W nowej wersji (Zadanie 3) zmieniono podejście na **Aktywność jako listener**:
* Klasa `MainActivity` deklaruje implementację interfejsu `implements View.OnClickListener`.
* Metoda `onCreate` przypisuje całą aktywność do przycisków za pomocą `setOnClickListener(this)`.
* Cała logika rozróżniania przycisków została przeniesiona do jednej ogólnej metody `onClick(View v)`, w której za pomocą instrukcji warunkowych (`if / else if`) sprawdzane jest `v.getId()`.

## 2. Która wersja jest krótsza?
Wersja z **lambdami (Zadanie 2)** jest krótsza i bardziej zwięzła. Kod odpowiedzialny za reakcję na kliknięcie znajduje się bezpośrednio w miejscu deklaracji przycisku, co eliminuje potrzebę tworzenia rozbudowanych instrukcji warunkowych (`if-else` lub `switch`) sprawdzających identyfikatory widoków.

## 3. Którą wersję łatwiej byłoby rozbudować o kolejne pięć przycisków?
Do rozbudowy o kolejne pięć przycisków wygodniejsza i bardziej czytelna byłaby **wersja z lambdami** (lub osobnymi mniejszymi listenerami):
* Przy lambdach każdy przycisk ma swoją niezależną logikę zamkniętą w osobnym bloku kodu, co zapobiega rozrastaniu się jednej gigantycznej metody `onClick`.
* W przypadku podejścia z aktywnością jako listener (`onClick`), dodanie kolejnych pięciu przycisków zmusiłoby programistę do dopisania kolejnych pięciu warunków do wspólnej metody `onClick`, co przy dużej liczbie elementów prowadzi do powstania trudnego w utrzymaniu kodu.