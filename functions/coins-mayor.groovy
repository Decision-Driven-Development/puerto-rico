def coins = context.valueFor('board', 'mayor');
coins.isNumber() ? coins.toInteger() + 1 : 0