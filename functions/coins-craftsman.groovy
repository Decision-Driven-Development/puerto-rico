def coins = context.valueFor('board', 'craftsman');
coins.isNumber() ? coins.toInteger() + 1 : 0