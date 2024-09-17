int numPlayers = context.valueFor('table', 'playersNum').toInteger()

context.valueFor('table', 'player' + Math.ceil(Math.random() * numPlayers).toInteger())