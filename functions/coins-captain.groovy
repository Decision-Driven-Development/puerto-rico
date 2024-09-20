def coins = context.valueFor('board', 'captain');
coins.isNumber() ? coins.toInteger() + 1 : 0