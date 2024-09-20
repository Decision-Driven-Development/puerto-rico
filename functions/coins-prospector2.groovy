def coins = context.valueFor('board', 'prospector2')
coins.isNumber() ? coins.toInteger() + 1 : 0