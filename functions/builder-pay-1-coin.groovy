def player = context.valueFor('name-to-id', 'id')
def coins = context.valueFor(player, 'coins').toInteger()

coins - 1