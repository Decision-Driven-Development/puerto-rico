def reserve = context.valueFor('board', "colonistReserve").toInteger()
reserve > 1 ? reserve - 1 : 0