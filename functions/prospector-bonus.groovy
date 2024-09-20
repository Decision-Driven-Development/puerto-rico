String player = context.valueFor('name-to-id', 'id')

int currentCoins = context.valueFor(player, 'coins').toInteger()
int prospectorBonus = 1

int newCoins = currentCoins + prospectorBonus

newCoins
