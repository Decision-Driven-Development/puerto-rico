---
title: Синтаксис таблиц принятия решений
date: 2024-10-02 15:45:00 +0400
layout: post
---

В общем, я попробовал написать условия для выполнения действия "Построить малую фабрику индиго бесплатно",
получилась вот такая таблица (привожу фрагмент, только одно правило):

```text
HDR ;Постройка малой фабрики индиго 1 ;1 каменоломня
CND ;name-to-id::id                   ;player-order::current
CND ;name-to-id::id                   ;!turnOrder::1
CND ;phase::role                      ;builder
CND ;smallIndigoPlant1::owner         ;market
CND ;function::active-quarries        ;>0
OUT ;available                        ;true                                ;false
ASG ;smallIndigoPlant1::owner         ;name-to-id::id
ASG ;${name-to-id::id}::acted         ;true
ASG ;temp::building                   ;smallIndigoPlant1
ASG ;${name-to-id::id}::buildings     ;function::add-building-to-player
ASG ;temp::building                   ;undefined
```

Похоже, что нужна пояснительная бригада :)


## Столбцы таблицы

Таблицы я храню в простых csv-файлах, разделителем значений выступает символ `;`, так "исторически сложилось".

Предлагаю нумеровать столбцы таблицы с нуля :) мы ж программисты.  В "нулевом" столбце указан тип 
каждой строки, это трехбуквенный символьный код, по которому прикладное приложение понимает,
какие операции допустимы со строкой (точнее, результатом ее разбора).

Про типы строк поговорим отдельно, пока двинемся дальше по столбцам.

В первом столбце всегда записано какое-то "базовое" значение, его смысл будет разным для
разных типов строк, но обычно это указание на тот фрагмент данных, с которым нужно что-то сделать.

Во втором и следующих столбцах содержатся правила принятия решений и выполнения действий. Все они
"собирают" свои условия и инструкции как пару - комбинацию базового значения из первого столбца таблицы и
значения из столбца конкретного правила.

В примере есть еще один столбец (последний), в котором нет ни одного значения в строках с условиями (типы
строк опишу чуть ниже) - это столбец с правилом "ИНАЧЕ". Если ни одно из явно описанных правил таблицы не
выполняется, то таблица вернет те значения, что указаны в столбце "ИНАЧЕ".

## Типы строк

На данный момент движок расчета таблиц умеет работать с тремя типами, но другие инструменты
могут добавлять обработку собственных типов строк.

Типы, которые поддерживает движок:

- `CND` - строка с описанием условий таблицы. В первом столбце такой строки содержится
  "базовое" значение (то, что сравниваем), а в столбцах правил - значения, с которыми 
  сравниваем. По умолчанию, если нет других логических операторов, подразумевается проверка
  на равенство, поэтому в таблице выше, скажем, четвертое условие будет читаться как 
  `smallIndigoPlant1::owner = market`, то есть, проверяем, что карточка малой фабрики индиго
  сейчас находится на "рынке зданий".
- `OUT` - строка с описанием одного выходного значения таблицы. Из любой другой таблицы или
  функции можно обратиться к этому значению, запустив расчет таблицы в соответствующем режиме
  (см. [Введение в таблицы принятия решений]({% post_url 2024-09-30-introduction-to-decision-tables %}#computation-modes)).
  Выходных значений может быть несколько, для каждого из них нужна отдельная строка. 
  В таблице выше выходное значение всего
  одно - `available`, по смыслу это булев флаг доступности действия.
- `ASG` - инструкция по изменению состояния системы. Такие строки предписывают записать в 
  "базовый" фрагмент данных значение из столбца конкретного правила. В таблице выше третья
  инструкция (пара `temp::building - smallIndigoPlant1`) предписывает сохранить строку 
  `smallIndigoPlant1` по адресу `temp::building`.

У меня в примере есть еще один тип строк - `HDR`, это строка заголовка, движок расчета таблиц
ее игнорирует.

## Динамически вычисляемые значения

Во-первых, если любая ячейка таблицы содержит `::`, то движок понимает, что это динамически
вычисляемое значение. Вообще, конечно, о доступе к данным можно поговорить отдельно, тут пока
я просто скажу, что поскольку каждое условие всегда сводимо к сравнению двух каких-то 
примитивных значений, то и обращаться к данным можно по одному значению за раз. Отсюда и 
родилась такая двухуровневая абстракция (и запись) для доступа к любым данным в ходе расчета: 

```
locator::fragment
```

- `locator` - это название какого-то пространства имен, отвечающего за (предпочтительно) логически
  связные данные. Реализация Локаторов может быть абсолютно любой: это может быть и репозиторий, 
  обращающийся к БД; и http-адаптер, выполняющий запросы к сторонней системе; и какой-нибудь кэш
  прямо в памяти. Более того, каждая таблица принятия решений - это тоже Локатор.
  В этом конкретном примере есть Локатор `phase` - в нем собраны разные параметры 
  текущей фазы игры. А вот Локатор `name-to-id` - это вспомогательная таблица для расчета идентификатора
  игрока за столом.
- `fragment` - это название конкретного фрагмента данных, уникальное в пространстве имен. Можно 
  думать о нем как об одном поле записи в БД, одном поле в JSON или одном выходном значении таблицы
  принятия решений. 

Вообще говоря, для движка все значения в таблицах - динамически вычисляемые. Просто те значения, в 
которых нет символов `::` преобразуются в `constant::xxxxxxx` и специальный Локатор просто возвращает
`fragment`-часть адреса как выходное значение.

Все остальные значения вычисляются путем обращения к указанному Локатору за указанным Фрагментом.

Иногда конкретный Фрагмент (или даже Локатор) могут быть неизвестны на этапе написания таблиц (но будут
известны в рантайме, во время расчета). Например, после выполнения игрового действия нужно пометить текущего
игрока, как выполнившего ход, но конкретный игрок на момент написания таблицы неизвестен. Поэтому в таблице
есть записи вроде `ASG ;${name-to-id::id}::acted         ;true`, во время вычисления таблицы часть в
фигурных скобках будет динамически вычислена и превратится, например, в `player2`, и в итоге получится 
инструкция записать в адрес `player2::acted` значение `true`.



Если что-то упустил - наверстаю в следующих постах.