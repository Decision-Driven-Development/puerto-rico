def coins = context.valueFor('board', 'prospector1');
coins.isNumber() ? coins.toInteger() + 1 : 0