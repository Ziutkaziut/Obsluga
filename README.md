Po etapie 1: Zmianę trzeba wprowadzić w wielu miejscach (osobno w każdej lambdzie dla każdego przycisku cyfry – w 10 różnych miejscach dla cyfr od 0 do 9).

Po etapie 3: Zmianę wystarczy wprowadzić w jednym miejscu – bezpośrednio w metodzie pomocniczej appendSymbol(), ponieważ wszystkie cyfry korzystają z tego samego wspólnego listenera (digitListener) i przechodzą przez tę metodę.