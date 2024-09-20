def coins = context.valueFor('board', 'trader')
coins.isNumber() ? coins.toInteger() + 1 : 0