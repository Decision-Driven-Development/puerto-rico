---
title: Репозиторий с таблицами и интерпретатор тестов 
date: 2024-10-11 14:55:00 +0400
layout: post
comments: true
---

Добрался-таки до слияния репозиториев, до этого момента таблицы у меня жили в одном репозитории,
а дневник разработчика - в другом. Теперь за все отвечает один репозиторий, вот 
[ссылка](https://github.com/Decision-Driven-Development/puerto-rico).

Заодно еще и заменил разделители столбцов в файлах таблиц, теперь использую запятую, а не точку 
с запятой. Это дало возможность красиво рендерить таблицы в веб-интерфейсе GitHub, к тому же в 
постах я теперь смогу ссылаться на конкретные файлы / gists, и они тоже будут красиво 
отформатированы.

А еще хочется рассказать про инструмент, которым я пользуюсь для проверки своих таблиц, но 
сначала придется рассказать еще и про _табличные тесты_.

Таблицы принятия решений состоят из двух частей: условий и действий. Интерпретатор таблиц обычно
проверяет все условия, а затем выполняет только те действия, которые соответствуют удовлетворенным
правилам. А если сначала выполнить действия, а затем проверить условия? Получится _табличный тест_.

Получается, что один и тот же синтаксис можно использовать как для описания логики, так и для
фиксации тестовых сценариев. Ну и написать инструмент, который будет выполнять табличные тесты,
иначе говоря, будет интерпретировать таблицы как тесты.

Базовый _табличный тест_ выглядит так:

{% gist f764f02994e7747e2902073419502648 %}

Для тестов мне понадобилось добавить к [базовым типам строк]({%post_url 2024-10-02-decision-tables-syntax %}#row-types)
таблицы еще один тип - `EXE` (от _execute_). В примере таких строк две, они содержат указания выполнить разные действия. 

В первом столбце первой строки стоит значение `include`, движок тестов интерпретирует это как 
указание выполнить все действия из другого тестового файла. Таким образом я могу переиспользовать 
в любых тестах состояния, полученные в других тестах или вспомогательных файлах. В этом примере
тест сначала выполняет действия, создающие игровой стол для трех игроков.

Во второй строке в первом столбце стоит значение `command`, это указание выполнить команду,
описанную в каком-то файле таблиц. В данном случае это команда инициализации игры, которая 
подготавливает игровой стол к началу партии - рассаживает игроков, выдает им стартовые ресурсы,
и так далее.

Следующие две строки тоже относятся к созданию предварительных условий для теста, они 
устанавливают текущего игрока и задают состояние роли "Поселенец". Эти строки имеют тип `ASG`,
то есть они изменяют состояние системы.

Последняя строка - это собственно проверка теста. Это строка с типом `CND`, в данном примере она
проверяет, что действие "Выбрать роль Поселенца" доступно. Если это условие выполнено, то тест
считается пройденным.

Интерпретатор тестов - это отдельное веб-приложение, которое "смотрит" на папку с таблицами и тестами,
прогоняет тесты и отображает результаты. Интерпретатор пока очень сырой в плане интерфейса и возможностей,
но его базовых способностей вполне достаточно для оценки правильности таблиц. Его исходники расположены
в [отдельном репозитории](https://github.com/Decision-Driven-Development/logic-checker).