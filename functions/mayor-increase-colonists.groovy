def player = context.valueFor('name-to-id', 'id')
def colonists = context.valueFor(player, 'colonists').toInteger()

colonists + 1