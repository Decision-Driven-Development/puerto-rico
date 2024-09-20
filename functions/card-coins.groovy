String player = context.valueFor('name-to-id', 'id')
int currentCoins = context.valueFor(player, 'coins').toInteger()
int coinsOnCard = context.valueFor('phase', 'coins').toInteger()

int newCoins = currentCoins + coinsOnCard
newCoins
