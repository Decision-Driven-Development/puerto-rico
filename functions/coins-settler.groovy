def coins = context.valueFor('board', 'settler')
coins.isNumber() ? coins.toInteger() + 1 : 0