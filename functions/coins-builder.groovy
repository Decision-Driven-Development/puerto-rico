def coins = context.valueFor('board', 'builder');
coins.isNumber() ? coins.toInteger() + 1 : 0